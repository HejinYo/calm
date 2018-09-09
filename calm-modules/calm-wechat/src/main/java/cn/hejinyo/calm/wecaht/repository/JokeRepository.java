package cn.hejinyo.calm.wecaht.repository;

import cn.hejinyo.calm.wecaht.model.JokeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : HejinYo   hejinyo@gmail.com
 * @date :  2018/7/19 23:13
 */
public interface JokeRepository extends JpaRepository<JokeEntity, Integer> {

    /**
     * 更新部分字段
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update JokeEntity set content=:content where id=:id")
    int update(@Param("id") Integer id, @Param("content") String content);
}
