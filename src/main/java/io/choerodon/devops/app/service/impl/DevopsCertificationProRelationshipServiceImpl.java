package io.choerodon.devops.app.service.impl;

import java.util.List;

import io.choerodon.core.exception.CommonException;
import io.choerodon.devops.app.service.DevopsCertificationProRelationshipService;
import io.choerodon.devops.infra.dto.DevopsCertificationProRelationshipDTO;
import io.choerodon.devops.infra.mapper.DevopsCertificationProRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zmf
 */
@Service
public class DevopsCertificationProRelationshipServiceImpl implements DevopsCertificationProRelationshipService {
    @Autowired
    private DevopsCertificationProRelMapper devopsCertificationProRelMapper;

    @Override
    public void baseInsertRelationship(DevopsCertificationProRelationshipDTO devopsCertificationProRelationshipDTO) {
        if (devopsCertificationProRelMapper.insert(devopsCertificationProRelationshipDTO) != 1) {
            throw new CommonException("error.devops.cert.project.rel.add.error");
        }
    }

    @Override
    public List<DevopsCertificationProRelationshipDTO> baseListByCertificationId(Long certificationId) {
        DevopsCertificationProRelationshipDTO devopsCertificationProRelDO = new DevopsCertificationProRelationshipDTO();
        devopsCertificationProRelDO.setCertId(certificationId);
        return devopsCertificationProRelMapper.select(devopsCertificationProRelDO);
    }

    @Override
    public void baseDelete(DevopsCertificationProRelationshipDTO devopsCertificationProRelationshipDTO) {
        devopsCertificationProRelMapper.delete(devopsCertificationProRelationshipDTO);
    }

    @Override
    public void baseDeleteByCertificationId(Long certificationId) {
        DevopsCertificationProRelationshipDTO devopsCertificationProRelationshipDTO = new DevopsCertificationProRelationshipDTO();
        devopsCertificationProRelationshipDTO.setCertId(certificationId);
        devopsCertificationProRelMapper.delete(devopsCertificationProRelationshipDTO);
    }
}