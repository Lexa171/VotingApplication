package by.ots.mapper;

import by.ots.bean.Poll;
import by.ots.bean.PollOption;
import by.ots.dto.CreateOptionDto;
import by.ots.dto.CreatePollDto;
import by.ots.dto.OptionDto;
import by.ots.dto.StatisticPollOptionDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class PollOptionMapper {

    public StatisticPollOptionDto convertToStatisticPollOptionDto(Poll poll, PollOption pollOption) {
        final StatisticPollOptionDto statisticPollOptionDto = new StatisticPollOptionDto();
        Optional.of(poll).ifPresent(localPoll -> {
            statisticPollOptionDto.setPollId(localPoll
                    .getId());
            statisticPollOptionDto.setPollName(localPoll
                    .getName());
            statisticPollOptionDto.setPollKey(localPoll
                    .getKey());
        });

        Optional.of(pollOption).ifPresent(localPollOption -> {
            statisticPollOptionDto.setPollOptionId(localPollOption
                    .getId());
            statisticPollOptionDto.setOptionName(localPollOption
                    .getName());
            statisticPollOptionDto.setNumberOfVotes(localPollOption
                    .getNumberOfVotes());
        });

        return statisticPollOptionDto;
    }

    public PollOption convertCreateOptionDtoToPollOption(CreateOptionDto createOptionDto) {
        return Optional.of(createOptionDto).map(localCreateOptionDto -> {
            PollOption pollOption = new PollOption();
            pollOption.setName(localCreateOptionDto.getOptionName());
            pollOption.setNumberOfVotes(0);
            return pollOption;
        }).orElse(null);

    }

    public OptionDto convertPollOptionToOptionDto(PollOption pollOption) {
        return Optional.of(pollOption).map(localPollOption -> {
            OptionDto optionDto = new OptionDto();
            optionDto.setPollOptionId(localPollOption.getId());
            optionDto.setOptionName(localPollOption.getName());
            optionDto.setNumberOfVotes(localPollOption.getNumberOfVotes());
            return optionDto;
        }).orElse(null);
    }

    public List<OptionDto> convertToListOptionDto(List<PollOption> pollOptions) {
        return Optional.ofNullable(pollOptions)
                .map(localPollOptions -> localPollOptions.stream()
                        .map(this::convertPollOptionToOptionDto)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public List<PollOption> convertToListPollOption(List<CreateOptionDto> options) {
        return Optional.ofNullable(options)
                .map(localOptions -> localOptions.stream()
                        .map(this::convertCreateOptionDtoToPollOption)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
    public PollOption convertPollOption(PollOption pollOption,Poll poll) {
        pollOption.setPoll(poll);
        return pollOption;
    }
}
