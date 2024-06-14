package com.aspire.nsmp.cache.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * com.aspire.comp.entity.ComponentEntity
 *
 * @author ljh
 **/
@Data
@TableName(value = "component")
public class ComponentEntity {
    @TableId(value = "comp_id", type = IdType.INPUT)
    private String compId;
    @TableField(value = "`group`")
    private String group;
    @TableField(value = "`name`")
    private String name;
    @TableField(value = "`version`")
    private String version;
    @TableField(value = "asset_id")
    private String assetId;
    @TableField(value = "asset_download_url")
    private String assetDownloadUrl;
    @TableField(value = "asset_fileSize")
    private Long assetFileSize;
    @TableField(value = "asset_contentType")
    private String assetContentType;
    @TableField(value = "asset_created")
    private Date assetCreated;
    @TableField(value = "asset_lastModified")
    private Date assetLastModified;
    @TableField(value = "asset_md5")
    private String assetMd5;
    @TableField(value = "asset_sha1")
    private String assetSha1;
    @TableField(value = "asset_sha256")
    private String assetSha256;
    @TableField(value = "asset_sha512")
    private String assetSha512;
    @TableField(value = "asset_url")
    private String assetUrl;
    @TableField(value = "repo_format")
    private String repoFormat;
    @TableField(value = "repo_path")
    private String repoPath;
    @TableField(value = "repo_groupId")
    private String repoGroupId;
    @TableField(value = "repo_artifactId")
    private String repoArtifactId;
    @TableField(value = "repo_name")
    private String repoName;
    @TableField(value = "repo_platform")
    private String repoPlatform;
    @TableField(value = "repo_version")
    private String repoVersion;
    @TableField(value = "repo_extension")
    private String repoExtension;
    @TableField(value = "repo_classifier")
    private String repoClassifier;
    @TableField(value = "repo_download_url")
    private String repoDownloadUrl;
    // 新增
    @TableField(value = "asset_desc")
    private String assetDesc;
    @TableField(value = "asset_lic_name")
    private String assetLicName;
    @TableField(value = "asset_pom_name")
    private String assetPomName;
}
