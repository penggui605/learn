package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    private Novel novel;

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

 @RequestMapping("/add_avatar/{id}")
    public ModelAndView addNovelAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("novel", novelService.getNovel(id));
        mav.setViewName("novel_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddNovelrAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Novel novel = novelService.getNovel(id);
            novel.setAvatar(fileName);
            novelService.modifyNovel(novel);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/novel/list");
    }
   
}