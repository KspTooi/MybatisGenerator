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
@RequestMapping("/zskLawApply")
@Api(tags = "ZskLawApply")
@Slf4j
public class ZskLawApplyController {

    @Autowired
    private IZskLawApplyService iZskLawApplyService;

    @ApiOperation("条件查询")
    @PostMapping("/list")
    public TableDataInfo selectByDto(@RequestBody ZskLawApplyDto zskLawApplyDto) {
        return iZskLawApplyService.selectByDto(zskLawApplyDto);
    }

    @ApiOperation("以id查询单条记录")
    @GetMapping("/selectById")
    public R selectById(Long id) {
        if (id == null) {
            R.fail("id为必填参数");
        }
        return R.ok(iZskLawApplyService.selectById(id));
    }

    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody ZskLawApplyDto dto) {
        return R.ok(iZskLawApplyService.saveOrUpdate(dto));
    }

    @ApiOperation("以ids删除")
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        if (ids == null || ids.length == 0) {
            R.fail("ids为必填参数");
        }
        return R.ok(iZskLawApplyService.deleteByIds(ids));
    }

}