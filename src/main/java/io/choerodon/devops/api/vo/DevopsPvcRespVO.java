package io.choerodon.devops.api.vo;

import io.swagger.annotations.ApiModelProperty;

public class DevopsPvcRespVO extends DevopsResourceDataInfoVO {
    @ApiModelProperty("PVC id")
    private Long id;

    @ApiModelProperty("PVC名称")
    private String name;

    @ApiModelProperty("PVC绑定环境ID")
    private Long envId;

    @ApiModelProperty("PVC绑定PV id")
    private Long pvId;

    @ApiModelProperty("访问模式")
    private String accessModes;

    @ApiModelProperty("资源申请数量")
    private String requestResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEnvId() {
        return envId;
    }

    public void setEnvId(Long envId) {
        this.envId = envId;
    }

    public Long getPvId() {
        return pvId;
    }

    public void setPvId(Long pvId) {
        this.pvId = pvId;
    }

    public String getAccessModes() {
        return accessModes;
    }

    public void setAccessModes(String accessModes) {
        this.accessModes = accessModes;
    }

    public String getRequestResource() {
        return requestResource;
    }

    public void setRequestResource(String requestResource) {
        this.requestResource = requestResource;
    }
}
