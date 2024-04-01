package ${pkgNameController};

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${pkgNameService}.${serviceName};
import ${pkgNamePo}.${voName}InVo;
import ${pkgNamePo}.*;
import ${pkgNameVo}.*;

@RestController
@RequestMapping("/${fieldControllerName}")
@Api(tags = "${classTableName}")
@Slf4j
public class ${controllerName} {

    @Autowired
    private ${serviceName} ${fieldServiceName};

    @ApiOperation("列表查询")
    @PostMapping("/getMany")
    public TableDataInfo getMany(@RequestBody ${poName} ${fieldVoName}) {
        return ${fieldServiceName}.getMany(${fieldVoName});
    }

    @ApiOperation("保存或更新")
    @PostMapping("/saveOrUpdate")
    public R<String> saveOrUpdate(@RequestBody ${voName}InVo ${fieldVoName}) {
        return R.ok(${fieldServiceName}.saveOrUpdate(${fieldVoName}));
    }

    #foreach ($field in $fields)
    #if(${field.primary})
    @ApiOperation("根据主键查询单条记录")
    @PostMapping("/getBy${field.javaGetterName}")
    public R<${poName}> getBy${field.javaGetterName}(@RequestBody ${field.javaType} ${field.javaFieldName}) {
        if (id == null) {
            R.fail("id为必填参数");
        }
        return R.ok(${fieldServiceName}.getBy${field.javaGetterName}(${field.javaFieldName}));
    }

    @ApiOperation("根据主键删除记录")
    @PostMapping("/removeBy${field.javaGetterName}")
    public R removeBy${field.javaGetterName}(@RequestBody ${field.javaType} ${field.javaFieldName}) {
        if (id == null) {
            R.fail("id为必填参数");
        }
        return R.ok(${fieldServiceName}.removeBy${field.javaGetterName}(${field.javaFieldName}));
    }
    #end
    #end


}