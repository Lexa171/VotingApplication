package by.ots.mapper;


import by.ots.bean.Poll;

import by.ots.bean.PollOption;

import by.ots.bootProperties.Property;
import by.ots.dto.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Component
public class PollMapper {

    @Autowired
    private PollOptionMapper pollOptionMapper;

    @Autowired
    private Property property;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public PollDto convertPollToPollDto(Poll poll) {
        return Optional.of(poll).map(localPoll -> {
            PollDto pollDto = new PollDto();
            pollDto.setId(localPoll
                    .getId());
            pollDto.setPollName(localPoll
                    .getName());
            pollDto.setPollKey(localPoll
                    .getKey());
            pollDto.setPollStatus(localPoll
                    .getStatus());
            pollDto.setOptions(convertToListOptionDto(localPoll
                    .getPollOptions()));
            return pollDto;
        }).orElse(null);
    }

    public Poll convertCreatePollDtoToPoll(CreatePollDto createPollDto) {
        return Optional.of(createPollDto).map(localCreatePollDto -> {
            Poll poll = new Poll();
            poll.setName(localCreatePollDto.getPollName());
            poll.setPollOptions(convertToListPollOption(localCreatePollDto.getOptions()));
            poll.setStatus(true);
            return poll;
        }).orElse(null);
    }

    public PollStartDto convertToPollStartDto(String pollKey) {
        PollStartDto pollStartDto = new PollStartDto();
        pollStartDto.setPollKey(pollKey);
        try {
            pollStartDto.setUrl(new URI(property.bootConfig()
                    .getVoting()
                    .get("protocol") + "://" +
                    property.bootConfig()
                            .getVoting()
                            .get("host") + ":"
                    + property.bootConfig()
                    .getVoting()
                    .get("port")
                    + "/poll/ref/" + pollKey));

        } catch (URISyntaxException e) {
            logger.error("Request raised a URISyntaxException");
        }
        return pollStartDto;
    }

    public StatisticPollOptionDto convertToStatisticPollOptionDto(Poll poll, PollOption pollOption) {
        return pollOptionMapper.convertToStatisticPollOptionDto(poll, pollOption);
    }

    public PollOption convertCreateOptionDtoToPollOption(CreateOptionDto createOptionDto) {
        return pollOptionMapper.convertCreateOptionDtoToPollOption(createOptionDto);
    }

    public OptionDto convertPollOptionToOptionDto(PollOption pollOption) {
        return pollOptionMapper.convertPollOptionToOptionDto(pollOption);
    }

    public List<PollOption> convertToListPollOption(List<CreateOptionDto> options) {
        return pollOptionMapper.convertToListPollOption(options);
    }

    public List<OptionDto> convertToListOptionDto(List<PollOption> pollOptions) {
        return pollOptionMapper.convertToListOptionDto(pollOptions);
    }
    public PollOption convertPollOption(PollOption pollOption,Poll poll) {
        return pollOptionMapper.convertPollOption(pollOption,poll);
    }
}
