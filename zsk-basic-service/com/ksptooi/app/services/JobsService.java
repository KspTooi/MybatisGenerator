package com.ksptooi.app.services;

import com.ksptooi.app.model.po.*;
import com.ksptooi.app.model.vo.*;

@Service
public class JobsService{

    @Autowired
    private JobsService jobsMapper;

    @Autowired
    private Snowflake idWorker;

    @Override
    public TableDataInfo<JobsPo> getMany(JobsPo vo){
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        //PageHelper.orderBy("create_time desc");
        List<JobsPo> vos = jobsMapper.getMany(vo);
        return BaseController.getDataTable(vos);
    }

    @Override
    public String saveOrUpdate(JobsInVo in) {

        final JobsPo po = new JobsPo();

        po.setId(in.getId);
        po.setQueue(in.getQueue);
        po.setPayload(in.getPayload);
        po.setAttempts(in.getAttempts);
        po.setReservedAt(in.getReservedAt);
        po.setAvailableAt(in.getAvailableAt);
        po.setCreatedAt(in.getCreatedAt);

        return jobsMapper.insertOrUpdate()+"";
    }

    @Override
    public JobsPo getById(Long id) {
        return jobsMapper.getById(id);
    }

    @Override
    public int removeById(Long id) {
        return jobsMapper.removeById(id)
    }



}