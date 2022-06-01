package com.ruoyi.yjy.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.ThemeMapper;
import com.ruoyi.yjy.domain.Theme;
import com.ruoyi.yjy.service.IThemeService;

/**
 * 会议注意Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-28
 */
@Service
public class ThemeServiceImpl implements IThemeService 
{
    @Autowired
    private ThemeMapper themeMapper;

    /**
     * 查询会议注意
     * 
     * @param id 会议注意主键
     * @return 会议注意
     */
    @Override
    public Theme selectThemeById(Long id)
    {
        return themeMapper.selectThemeById(id);
    }

    /**
     * 查询会议注意列表
     * 
     * @param theme 会议注意
     * @return 会议注意
     */
    @Override
    public List<Theme> selectThemeList(Theme theme)
    {
        return themeMapper.selectThemeList(theme);
    }

    /**
     * 新增会议注意
     * 
     * @param theme 会议注意
     * @return 结果
     */
    @Override
    public int insertTheme(Theme theme)
    {
        theme.setCreateTime(DateUtils.getNowDate());
        return themeMapper.insertTheme(theme);
    }

    /**
     * 修改会议注意
     * 
     * @param theme 会议注意
     * @return 结果
     */
    @Override
    public int updateTheme(Theme theme)
    {
        return themeMapper.updateTheme(theme);
    }

    /**
     * 批量删除会议注意
     * 
     * @param ids 需要删除的会议注意主键
     * @return 结果
     */
    @Override
    public int deleteThemeByIds(Long[] ids)
    {
        return themeMapper.deleteThemeByIds(ids);
    }

    /**
     * 删除会议注意信息
     * 
     * @param id 会议注意主键
     * @return 结果
     */
    @Override
    public int deleteThemeById(Long id)
    {
        return themeMapper.deleteThemeById(id);
    }
}
