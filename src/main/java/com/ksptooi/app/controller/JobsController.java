package com.ksptooi.app.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ksptooi.app.model.dto.ZskLawApplyDto;
import com.ksptooi.app.services.IZskLawApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ksptooi.app.model.po.*
import com.ksptooi.app.model.vo.*

@RestController
@RequestMapping("/jobsController")
@Api(tags = "Jobs")
@Slf4j
public class JobsController {

    @Autowired
    private JobsService jobsService;

    @ApiOperation("列表查询")
    @PostMapping("/getMany")
    public TableDataInfo<JobsPo> getMany(@RequestBody JobsInVo jobs) {
        return jobsService.getMany(jobs);
    }



    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public R<JobsPo> saveOrUpdate(@RequestBody JobsInVo jobs) {
        return R.ok(jobsService.saveOrUpdate(jobs));
    }

    @ApiOperation("根据主键查询单条记录")
    @PostMapping("/getById")
    public R<JobsPo> getById(@RequestBody Long id) {
        if (id == null) {
            R.fail("id为必填参数");
        }
        return R.ok(jobsService.getById(id));
    }

    @ApiOperation("根据主键删除记录")
    @PostMapping("/removeById")
    public R removeById(@RequestBody Long id) {
        if (ids == null) {
            R.fail("id为必填参数");
        }
        return R.ok(jobsService.deleteById(id));
    }


}