package cn.hejinyo.calm.wecaht.controller;

import cn.hejinyo.calm.common.basis.utils.Result;
import cn.hejinyo.calm.wecaht.model.JokeEntity;
import cn.hejinyo.calm.wecaht.repository.JokeRepository;
import cn.hejinyo.calm.wecaht.service.WechatJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date : 2017/8/13 10:48
 */
@RestController
@RequestMapping(value = "/joke")
public class JokeController {

    @Autowired
    private WechatJokeService wechatJokeService;

    @Autowired
    private JokeRepository jokeRepository;

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        JokeEntity jokeEntity = jokeRepository.findById(id).orElse(null);
        if (jokeEntity == null) {
            return Result.error("笑话不存在");
        }
        return Result.ok(jokeEntity);
    }

    @PostMapping
    public Result save(@RequestBody JokeEntity jokeEntity) {
        if (jokeRepository.save(jokeEntity).getId() != null) {
            return Result.ok(jokeEntity);
        }
        return Result.error();
    }

    @PostMapping(value = "/{id}")
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

    @RequestMapping("/findOne")
    public Result joke() {
        return Result.ok(wechatJokeService.getRandom());
    }

}
