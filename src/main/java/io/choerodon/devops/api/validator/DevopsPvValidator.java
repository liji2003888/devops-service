package io.choerodon.devops.api.validator;

import java.util.regex.Pattern;

import io.choerodon.core.exception.CommonException;
import io.choerodon.devops.infra.enums.VolumeTypeEnum;
import io.kubernetes.client.models.V1HostPathVolumeSource;
import io.kubernetes.client.models.V1NFSVolumeSource;

public class DevopsPvValidator {

    private static final String SERVER_PATTERN = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2}\\.){3}((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})";

    private static final String HOSTPATH_PATTERN = "^/([-\\w]+[.]*[-\\w]*/?)+";

    private DevopsPvValidator(){

    }

    //校验存储类型的值是否为空或者格式是否符合要求
    public static void checkConfigValue(Object object, VolumeTypeEnum type) {
        switch (type) {
            case HOSTPATH:
                V1HostPathVolumeSource hostPath = (V1HostPathVolumeSource) object;
                if (hostPath.getPath() == null) {
                    throw new CommonException("pv.hostpath.path.not.found");
                } else if (!Pattern.matches(HOSTPATH_PATTERN, hostPath.getPath())) {
                    throw new CommonException("pv.hostpath.format.error");
                }
                break;
            case NFS:
                V1NFSVolumeSource nfs = (V1NFSVolumeSource) object;
                if (nfs.getPath() == null) {
                    throw new CommonException("pv.nfs.path.not.found");
                } else if (!Pattern.matches(HOSTPATH_PATTERN, nfs.getPath())) {
                    throw new CommonException("pv.nfs.path.format.error");
                } else if (nfs.getServer() == null) {
                    throw new CommonException("pv.nfs.server.not.ip");
                } else if (!Pattern.matches(SERVER_PATTERN, nfs.getServer())) {
                    throw new CommonException("pv.nfs.server.format.error");
                }
                break;
        }
    }

}
