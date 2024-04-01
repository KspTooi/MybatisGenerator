package ${packetNameMapper};


@Service
public class ${mapperName}{

    @Autowired
    private ZskLawApplyMapper zskLawApplyMapper;

    @Override
    public TableDataInfo selectByDto(ZskLawApplyDto dto){
        List<ZskLawApplyVo> vos = zskLawApplyMapper.selectByDto(dto);
        return BaseController.getDataTable(vos);
    }

    @Override
    public String saveOrUpdate(ZskLawApplyDto dto) {
        String dtoId = dto.getSid();
        Date nowDate = new Date();
        ZskLawApply zskLawApply = new ZskLawApply();
        Long uid = SecurityUtils.getLoginUser().getSysUser().getUserId();

        zskLawApply.setUpdateBy(uid);
        zskLawApply.setUpdateDate(nowDate);

        if(dtoId == null){
            long id = IdWorker.getId();
            zskLawApply.setCreateDate(nowDate);
            zskLawApply.setCreateBy(uid);
            zskLawApplyMapper.insert(zskLawApply);
            return id + "";
        } else {
            zskLawApplyMapper.updateById(zskLawApply);
        }
        return dtoId;
    }

    @Override
    public ZskLawApply selectById(Long id) {
        return zskLawApplyMapper.selectById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return zskLawApplyMapper.logicDeleteByIds(ids,SecurityUtils.getLoginUser().getSysUser().getUserId()+"",new Date());
    }

}