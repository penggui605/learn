package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Novel;

public interface NovelDao extends JpaRepository<Novel, Long> {

}