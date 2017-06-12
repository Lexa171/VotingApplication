package by.ots.service;

import by.ots.bean.PollOption;
import by.ots.dto.*;
import java.util.Optional;

public interface PollService {

    PollDto findByKey(String key);

    void closePoll(String key);

    void  voteOnce(OptionSmallDto optionSmallDto,String key);

    StatisticPollOptionDto getStatistic(String key, Long id);

    PollDto savePoll(CreatePollDto createPollDto);

    PollStartDto  startPoll(Long id);

    Optional<PollOption> getOnePollOptionInPoll(String key, Long id);
}
