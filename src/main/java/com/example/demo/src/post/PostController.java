package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.GetPostsRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final PostProvider postProvider;
    /*
    @Autowired
    private final PostService postService;

     */
    @Autowired
    private final JwtService jwtService;

    public PostController(PostProvider postProvider, JwtService jwtService){
        this.postProvider = postProvider;
        //this.postService = postService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetPostsRes>> getPosts(@RequestParam int userIdx){
        try{
            //jwt에서 idx 추출.
            //int userIdxByJwt = jwtService.getUserIdx();
            List<GetPostsRes> getPosts=postProvider.retrievePosts(userIdx);

            return new BaseResponse<>(getPosts);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
