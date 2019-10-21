package edu.hubu.learn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Novel;
import edu.hubu.learn.service.NovelService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @RequestMapping("/{id}")
    public ModelAndView novel(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Novel novel = novelService.getNovel(id);
        mav.addObject("novel", novel);
        mav.setViewName("novel");
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        novelService.deleteNovel(id);
        ModelAndView mav = new ModelAndView("redirect:/novel/list");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView novels() {
        ModelAndView mav = new ModelAndView();
        List<Novel> novels = novelService.getNovels();
        mav.addObject("novels", novels);
        mav.setViewName("novels");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addNovel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("novel_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddNovel(Novel novel) {
        
        novelService.addNovel(novel);
        ModelAndView mav = new ModelAndView("redirect:/novel/list");
        return mav;
    }

    @RequestMapping("/modify/{id}")
    public ModelAndView modifyNovel(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("novel", novelService.getNovel(id));
        mav.setViewName("novel_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyNovel(Novel novel) {
        
        novelService.modifyNovel(novel);
        ModelAndView mav = new ModelAndView("redirect:/novel/list");
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView searchNovel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("novel_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchNovel(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Novel> novels = novelService.searchNovels(keyword);
        mav.addObject("novels", novels);
        mav.setViewName("novels");
        return mav;
    }


   
}