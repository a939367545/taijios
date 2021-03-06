package com.sghy1801.dao;

import com.sghy1801.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    public User login(@Param("phone") String phone, @Param("password") String password);

    /**
     * 查询用户列表页
     *
     * @param param
     * @return
     */
    public List<User> listUser(Map<String, Object> param);

    /**
     * 查询用户记录数
     *
     * @param param
     * @return
     */
    public int countUser(Map<String, Object> param);

    /**
     * 添加用户
     *
     * @param u
     * @return
     */
    public int addUser(User u);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int deleteUser(@Param("userId") int id);

    /**
     * 修改用户
     *
     * @param u
     * @return
     */
    public int updateUser(User u);

    /**
     * 根据id查询单个用户
     *
     * @param id
     * @return
     */
    public User findById(int id);


    public List<Map> getDate();

    /**
     * 修改用户状态
     * @param userId
     * @param changestate
     * @return
     */
    public int updateChanges(@Param("userId") int userId, @Param("changestate") int changestate);


    /**
     * 修改密码
     * @return
     */
    public int updatePass(Map<String, Object> param);


    /**
     * 拥有设备人数最多的前5个省份
     * @return
     */
    public List<Map> getLocalDistribution();

}
