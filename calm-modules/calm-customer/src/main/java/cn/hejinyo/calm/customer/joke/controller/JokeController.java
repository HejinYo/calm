package cn.hejinyo.calm.customer.joke.controller;

import cn.hejinyo.calm.customer.joke.model.JokeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/19 23:42
 */
@RestController
@RequestMapping("/joke")
public class JokeController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public JokeEntity findOne(@PathVariable Integer id) {
        return restTemplate.getForObject("http://CALM-PROVIDER/joke/" + id, JokeEntity.class);
    }

    /*@PostMapping
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
    }*/

}
