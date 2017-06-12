package by.ots.controller;

import by.ots.dto.*;
import by.ots.service.PollService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;


@RestController
@RequestMapping(value = {"/poll"}, produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
public class PollController {

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/ref/{key}", method = RequestMethod.GET)//5)Получение данных о голосовании (по сгенерированной ссылке)
    public ResponseEntity<PollDto> getPoll(@PathVariable("key") @NotEmpty String key) {
        return ResponseEntity.ok(pollService.findByKey(key));
    }

    @RequestMapping(value = "/ref/{key}/statistic", method = RequestMethod.GET)//4)Отображение статистики (в виде Выбранный пункт - количество)
    public ResponseEntity<StatisticPollOptionDto> getStatistic(@PathVariable("key") @NotEmpty String key,
                                                               @RequestParam("pollOptionId")
                                                               @Min(0) @NotNull Long pollOptionId) {
        return ResponseEntity.ok(pollService.getStatistic(key, pollOptionId));
    }

    @RequestMapping(value = "/ref/{key}/vote", method = RequestMethod.PUT)//6)Регистрация голоса
    public ResponseEntity<Void> vote(@PathVariable("key") @NotEmpty String key,
                                     @Valid @RequestBody OptionSmallDto optionSmallDto) {
        pollService.voteOnce(optionSmallDto, key);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/ref/{key}", method = RequestMethod.DELETE)//3)Закрытие голосования
    public ResponseEntity<Void> closePoll(@PathVariable("key") @NotEmpty String key) {
        pollService.closePoll(key);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)//1) создает темы для голосования
    public ResponseEntity<PollDto> addPoll(@Valid @RequestBody CreatePollDto createPollDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pollService.savePoll(createPollDto));
    }

    @RequestMapping(value = "/{id}/start", method = RequestMethod.POST)//2)Старт голосования с генерацией ссылки для голосования
    public ResponseEntity<PollStartDto> start(@PathVariable("id") @Min(0) @NotNull Long id) {
        return ResponseEntity.ok(pollService.startPoll(id));
    }
}

