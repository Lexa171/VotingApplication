package by.ots.repository;

import by.ots.bean.Poll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll,Long> {
    Optional<Poll> findByKey(String Key);
    Optional<Poll> findOneById(Long id);
}
