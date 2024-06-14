package com.aspire.nsmp.cache.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * com.aspire.comp.entity.ComponentEntity
 *
 * @author ljh
 **/
@Data
public class ComponentBo implements Serializable {

    private String group;

    private String name;

    private String version;

    private String assetId;

    private String assetDownloadUrl;

    private Long assetFileSize;

    private String assetContentType;

    private Date assetCreated;

    private Date assetLastModified;

    private String assetMd5;

    private String assetSha1;

    private String assetSha256;

    private String assetSha512;

    private String assetUrl;

    private String repoFormat;

    private String repoPath;

    private String repoGroupId;

    private String repoArtifactId;

    private String repoName;

    private String repoPlatform;

    private String repoVersion;

    private String repoExtension;

    private String repoClassifier;

    private String repoDownloadUrl;

    private String assetDesc;

    private String assetLicName;

    private String assetPomName;
}
