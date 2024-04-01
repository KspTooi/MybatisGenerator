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


@RestController
@RequestMapping("/jobsController")
@Api(tags = "Jobs")
@Slf4j
public class JobsController {

    @Autowired
    private IJobsService iJobsService;

    @ApiOperation("列表查询")
    @PostMapping("/getMany")
    public TableDataInfo<JobsPo> getMany(@RequestBody JobsPo jobsPo) {
        return iJobsService.getMany(jobsPo);
    }

    @ApiOperation("根据id查询单条记录")
    @PostMapping("/getById")
    public R<JobsPo> getById(@RequestBody Long id) {
        if (id == null) {
            R.fail("id为必填参数");
        }
        return R.ok(iJobsService.getById(id));
    }

    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public R<JobsPo> saveOrUpdate(@RequestBody JobsPo jobsPo) {
        return R.ok(iJobsService.saveOrUpdate(jobsPo));
    }

    @ApiOperation("根据IDS删除记录")
    @PostMapping("/{ids}")
    public R remove(@RequestBody Long[] ids) {
        if (ids == null || ids.length == 0) {
            R.fail("ids为必填参数");
        }
        return R.ok(iJobsService.deleteByIds(ids));
    }

}