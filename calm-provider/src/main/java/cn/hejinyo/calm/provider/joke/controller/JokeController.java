package cn.hejinyo.calm.provider.joke.controller;

import cn.hejinyo.calm.provider.joke.model.JokeEntity;
import cn.hejinyo.calm.provider.joke.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/19 23:21
 */
@RestController
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    private JokeRepository jokeRepository;

    @GetMapping("/{id}")
    public JokeEntity findOne(@PathVariable Integer id) {
        return jokeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public JokeEntity save(@RequestBody JokeEntity jokeEntity) {
        return jokeRepository.save(jokeEntity);
    }

    @PutMapping("/{id}")
    public JokeEntity update(@PathVariable Integer id, @RequestBody JokeEntity jokeEntity) {
        jokeEntity.setId(id);
        return jokeRepository.save(jokeEntity);
    }

    @PutMapping("/{id}/content")
    public int updateTime(@PathVariable Integer id, @RequestBody JokeEntity jokeEntity) {
        return jokeRepository.update(id, jokeEntity.getContent());
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        jokeRepository.deleteById(id);
        return 0;
    }
}
