package com.leyou.upload.serice;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    /**
     * FastDFS 文件上传对象
     */
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 获取UploadProperties类中配置的application.yml中的属性
     * 替代下方的ALLOW_TYPES
     */
    @Autowired
    private UploadProperties prop;

    //private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpg","image/jpeg", "image/png", "image/bmp");

    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!prop.getAllowTypes().contains(contentType)) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            /*//准备目标路径
            File dest = new File("E:/Java/Projects/upload", file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);*/

            //上传图片到FastDFS
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);

            /**返回图片保存的路径
             * http://image.leyou.com/group1/M00/00/00/wKiv4mAgziuAY6CSAAGiiex73CU056.jpg
             */
            return prop.getBaseUrl() + storePath.getFullPath();

        } catch (IOException e) {
            log.error("上传文件失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }


    }
}