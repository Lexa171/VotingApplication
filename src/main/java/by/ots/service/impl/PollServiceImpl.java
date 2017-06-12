package by.ots.service.impl;

import by.ots.bean.Poll;
import by.ots.bean.PollOption;
import by.ots.dto.*;
import by.ots.exception.ConflictException;
import by.ots.exception.NotFoundException;
import by.ots.mapper.PollMapper;
import by.ots.repository.PollOptionRepository;
import by.ots.repository.PollRepository;
import by.ots.service.PollService;
import by.ots.util.KeyGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("pollService")
@Transactional(readOnly = true)
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private PollMapper pollMapper;

    @Override
    public PollDto findByKey(String key) {
        return pollMapper.convertPollToPollDto(pollRepository.findByKey(key)
                .orElseThrow(() -> new NotFoundException("Voting not found")));
    }

    @Override
    @Transactional
    public void closePoll(String key) {
        Poll poll = pollRepository.findByKey(key)
                .orElseThrow(() -> new NotFoundException("Voting not found"));
        poll.setStatus(false);
        pollRepository.save(poll);
    }

    @Override
    @Transactional
    public void voteOnce(OptionSmallDto optionSmallDto, String key) {
        PollOption pollOption = getOnePollOptionInPoll(key, optionSmallDto.getPollOptionId())
                .orElseThrow(() -> new NotFoundException("Option not found"));
        pollOption.setNumberOfVotes(pollOption
                .getNumberOfVotes() + 1);
        pollOptionRepository.save(pollOption);
    }

    @Override
    public StatisticPollOptionDto getStatistic(String key, Long pollOptionId) {
        Poll poll = pollRepository.findByKey(key)
                .orElseThrow(() -> new NotFoundException("Voting not found"));
        PollOption pollOption = getOnePollOptionInPoll(key, pollOptionId)
                .orElseThrow(() -> new NotFoundException("Option not found"));

        return pollMapper.convertToStatisticPollOptionDto(poll, pollOption);
    }

    @Override
    @Transactional
    public PollDto savePoll(CreatePollDto createPollDto) {
        Poll poll = pollMapper.convertCreatePollDtoToPoll(createPollDto);
        Poll finalPoll = pollRepository.save(poll);
        finalPoll.getPollOptions()
                .stream()
                .map(localOption -> pollMapper.convertPollOption(localOption,finalPoll))
                .collect(Collectors.toList());

        return pollMapper.convertPollToPollDto( pollRepository.save(finalPoll));
    }

    @Override
    @Transactional
    public PollStartDto startPoll(Long id) {
        Poll poll = pollRepository.findOneById(id)
                .orElseThrow(() -> new NotFoundException("Voting not found"));
        if (poll.getStatus()) {
            poll.setKey(Optional.ofNullable(poll
                    .getKey())
                    .orElseGet(() -> KeyGenerationUtil
                            .generationKey(poll.getName(), id)));

            return pollMapper.convertToPollStartDto(pollRepository.save(poll).getKey());
        }
        throw new ConflictException("Voting is closed");
    }

    @Override
    public Optional<PollOption> getOnePollOptionInPoll(String key, Long pollOptionId) {
        Poll poll = pollRepository.findByKey(key)
                .orElseThrow(() -> new NotFoundException("Voting not found"));

        return poll.getPollOptions()
                .stream()
                .filter(localPollOption -> localPollOption
                        .getId()
                        .equals(pollOptionId))
                .findFirst();
    }
}
