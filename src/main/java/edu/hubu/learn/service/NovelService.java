package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.NovelDao;
import edu.hubu.learn.entity.Novel;

@Service
public class NovelService {

    @Autowired
    private NovelDao novelDao;

    public Novel getNovel(Long id) {
        return novelDao.findById(id).get();
    }

    public List<Novel> getNovels() {
        return novelDao.findAll(new Sort(Direction.DESC, "id"));
    }

    public List<Novel> searchNovels(String keyword) {
        Novel novel = new Novel();
        novel.setNovelname(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("novelname", match->match.contains());
        Example<Novel> example = Example.of(novel, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return novelDao.findAll(example, sort);
    }

    public Novel addNovel(Novel novel) {
        return novelDao.save(novel);
    }

    public void deleteNovel(Long id) {
        novelDao.deleteById(id);
    }

    public void modifyNovel(Novel novel) {
        novelDao.save(novel);
    }
}