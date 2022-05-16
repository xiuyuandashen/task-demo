package com.example.demo.service.impl;

import com.example.demo.entity.TbTask;
import com.example.demo.mapper.TbTaskMapper;
import com.example.demo.service.ITbTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态定时任务表 服务实现类
 * </p>
 *
 * @author author
 * @since 2022-05-13
 */
@Service
public class TbTaskServiceImpl extends ServiceImpl<TbTaskMapper, TbTask> implements ITbTaskService {

}
