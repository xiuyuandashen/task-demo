package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动态定时任务表
 * </p>
 *
 * @author author
 * @since 2022-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_task")
public class TbTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 定时任务id
     */
    @TableId(value = "task_id", type = IdType.NONE)
    private String taskId;

    /**
     * 定时任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 定时任务描述
     */
    @TableField("task_desc")
    private String taskDesc;

    /**
     * 定时任务Cron表达式
     */
    @TableField("task_exp")
    private String taskExp;

    /**
     * 定时任务状态，0停用 1启用
     */
    @TableField("task_status")
    private Integer taskStatus;

    /**
     * 定时任务的Runnable任务类完整路径
     */
    @TableField("task_class")
    private String taskClass;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
