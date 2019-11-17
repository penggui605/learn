package edu.hubu.learn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hubu.learn.entity.Novel;
import edu.hubu.learn.entity.Result;
import edu.hubu.learn.service.NovelService;

@RestController

@RequestMapping("/rest/novel")
public class NovelRestController {

    @Autowired
    private NovelService novelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable("id") long id) {
        return novelService.getNovel(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getAll() {
        return novelService.getNovels();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object addNovel(@RequestBody Novel novel) {
        novelService.addNovel(novel);
        return novel;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object modifyNovel(@RequestBody Novel novel) {
        novelService.modifyNovel(novel);
        return novel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("id") long id) {
        try {
            novelService.deleteNovel(id);
        } catch (Exception e) {
            return new Result(false, e);
        }
        return new Result(true, null);
    }

    @RequestMapping(value = "/search/{key}", method = RequestMethod.GET)
    public Object searchNovel(String key) {
        return novelService.searchNovels(key);
    }
}