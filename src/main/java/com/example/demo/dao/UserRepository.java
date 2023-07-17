package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//名稱搜尋users的資料(create.html)
	@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	User findByUsername(@Param("username") String username);

	//搜尋所有users的資料(users.html)
	@Query(value = "SELECT * FROM users WHERE enabled != false", nativeQuery = true)
	List<User> findAll();
	
	//帳號、密碼尋找user的資料返回有或沒有(login.html , data.html)
	@Query(value = "SELECT * FROM users WHERE username = :username AND password = :password AND enabled = true", nativeQuery = true)
    User findUser(@Param("username") String username, @Param("password") String password);
	
	//創建新帳號(create.html)
	@Modifying
	@Query(value = "INSERT INTO users (username, password, email, enabled) VALUES (:username, :password, :email, true)", nativeQuery = true)
	void saveUser(@Param("username") String username, @Param("password") String password, @Param("email") String email);

	//根據ID做資料更新(data.html,update.html)
	@Modifying
	@Query(value = "UPDATE users SET username = :username, password = :password, email = :email WHERE id = :id", nativeQuery = true)
	void updateUser(@Param("id") Long id, @Param("username") String username, @Param("password") String password, @Param("email") String email);

	//根據ID做資料刪除(data.html,delete.html)
	@Modifying
	@Query(value = "UPDATE users SET enabled = false WHERE id = :id", nativeQuery = true)
	void deleteUser(@Param("id") Long id);

	@Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
	User getUserById(Long id);

}