package com.ruoyi.yjy.service;

import java.util.List;
import com.ruoyi.yjy.domain.Theme;

/**
 * 会议注意Service接口
 * 
 * @author ruoyi
 * @date 2022-05-28
 */
public interface IThemeService 
{
    /**
     * 查询会议注意
     * 
     * @param id 会议注意主键
     * @return 会议注意
     */
    public Theme selectThemeById(Long id);

    /**
     * 查询会议注意列表
     * 
     * @param theme 会议注意
     * @return 会议注意集合
     */
    public List<Theme> selectThemeList(Theme theme);

    /**
     * 新增会议注意
     * 
     * @param theme 会议注意
     * @return 结果
     */
    public int insertTheme(Theme theme);

    /**
     * 修改会议注意
     * 
     * @param theme 会议注意
     * @return 结果
     */
    public int updateTheme(Theme theme);

    /**
     * 批量删除会议注意
     * 
     * @param ids 需要删除的会议注意主键集合
     * @return 结果
     */
    public int deleteThemeByIds(Long[] ids);

    /**
     * 删除会议注意信息
     * 
     * @param id 会议注意主键
     * @return 结果
     */
    public int deleteThemeById(Long id);
}
