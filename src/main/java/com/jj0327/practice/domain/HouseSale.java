package com.jj0327.practice.domain;

import lombok.Data;

/**
 * @author jinbao
 * @date 2019/7/30 17:03
 * @description:
 */
@Data
public class HouseSale {
    /**
     * 小区名称
     */
    private String buildingName;
    /**
     * 产权年限
     *  40年/50年/70年/永久
     */
    private Integer PropertyYear;
    /**
     * 房屋类型
     *  普通住宅/经济适用房/公寓/商住楼/酒店式公寓
     */
    private String HouseType;

    private Integer shi;
    private Integer hall;
    private Integer toilet;
    private Integer kitchen;
    private Integer balcony;

    /**
     * 建筑面积
     */
    private String buildingArea;
    /**
     * 适用面积
     */
    private String usedArea;
}
