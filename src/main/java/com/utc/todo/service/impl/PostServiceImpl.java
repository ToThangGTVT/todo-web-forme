package com.utc.todo.service.impl;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import com.utc.todo.exception.DateInputException;
import com.utc.todo.repository.PostRepo;
import com.utc.todo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;

    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Post save(String content, String strBegin, String strEnd, Customer customer) throws ParseException, DateInputException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date dateBegin = simpleDateFormat.parse(strBegin);
        Date dateEnd = simpleDateFormat.parse(strEnd);

        Post post = new Post();
        post.setContent(content);
        post.setCreateDate(Calendar.getInstance().getTime());
        post.setStartDate(dateBegin);
        post.setEndDate(dateEnd);
        post.setStatus("0");
        System.out.println(customer);
        post.setCustomer(customer);

        if (dateBegin.compareTo(dateEnd) > 0) {
            throw new DateInputException();
        }

        return postRepo.save(post);
    }
}
