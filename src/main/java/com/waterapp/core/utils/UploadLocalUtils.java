package com.waterapp.core.utils;

import com.waterapp.core.entity.UploadImgParam;
import com.waterapp.core.entity.UploadResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 上传本地文件工具类
 */
public class UploadLocalUtils {
    private static File tempPathFile;
    private static Log log = LogFactory.getLog(UploadLocalUtils.class);

    public void init() {
        tempPathFile = new File("d:\\temp\\buffer\\");
        if (!tempPathFile.exists()) tempPathFile.mkdirs();
    }

    //无定义文件名，默认以系统时间命名
    public static UploadResult uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return uploadFile(request, response, null);
    }

    //用户自定义文件名
    private static UploadResult uploadFile(HttpServletRequest request, HttpServletResponse response, String userFileName) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String actuallyUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";// 取得服务器真实路径
        String relativeUrl = "/upload/";
        String uploadPath = request.getParameter("uploadPath");
        if (uploadPath != null && !"".equals(uploadPath)) {
            actuallyUrl += uploadPath + "/";
            relativeUrl += uploadPath + "/";
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();// 创建factory
        factory.setSizeThreshold(4096);// 设置缓冲区大小，这里是4kb
        factory.setRepository(tempPathFile);// 设置缓冲区目录
        ServletFileUpload upload = new ServletFileUpload(factory);// 创建一个新文件上传处理程序
        upload.setSizeMax(4 * 1024 * 1024);// 设置最大文件尺寸，这里是4MB
        List<FileItem> fileItems = new ArrayList<>();// 开始读取上传信息
        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        Iterator<FileItem> iter = fileItems.iterator();// 依次处理每个上传的文件
        log.debug("待上传文件数量：" + fileItems.size());
        List<String> actuallyPath = new ArrayList<>();
        List<String> relativePath = new ArrayList<>();
        List<String> errorMsg = new ArrayList<>();
        int i = 0;
        while (iter.hasNext()) {
            FileItem item = iter.next();
            log.debug("正在处理：" + item.getFieldName());
            if (!item.isFormField()) {// 忽略其他不是文件域的所有表单信息
                String name = item.getName();
                long size = item.getSize();
                if ((name == null || name.equals("")) && size == 0) {
                    String temp = "没有选择上传文件！";
                    errorMsg.add(temp);
                    log.debug(temp);
                    continue;
                }
                assert name != null;
                String fileExt = name.substring(name.lastIndexOf("."));// 根据文件名获取扩展名
                userFileName = (userFileName == null) ? (DateUtils.getDateStr() + i) : userFileName;//如果没有用户自定义名，默认用系统时间
                String fileName = userFileName + fileExt;// 生成文件名=用户自定义名+扩展名
                File floder = new File(actuallyUrl);
                if (!floder.exists()) floder.mkdirs();
                //记录文件位置
                actuallyPath.add(actuallyUrl + fileName);
                relativePath.add(relativeUrl + fileName);
                log.debug("上传【" + name + "】中。。。");
                try {
                    File file = new File(actuallyUrl, fileName);
                    item.write(file);
                    log.debug("文件【" + name + "】上传成功！名为：【" + fileName + "】！");
                } catch (Exception e) {
                    String temp = "文件【" + name + "】上传失败！，" + e.getMessage();
                    errorMsg.add(temp);
                    log.debug(temp);
                    e.printStackTrace();
                }
            } else {
                // 这里添加对不是上传文件表单项的处理
                String temp = "文件类型有误！请重新选择文件！";
                errorMsg.add(temp);
                log.debug(temp);
            }
            i++;
        }
        String dataMsg = "上传文件成功！";
        Boolean flag = true;
        Integer errorNum = errorMsg.size();
        if (errorNum > 0) {
            dataMsg = "上传文件" + fileItems.size() + "个，失败" + errorNum + "个！";
            flag = false;
        }
        UploadResult result = new UploadResult();
        result.setSuccess(flag);
        result.setData(dataMsg);
        result.setError(errorMsg);
        result.setActuallyPath(actuallyPath);
        result.setRelativePath(relativePath);
        return result;
    }

    /**
     * 批量调整图片大小
     *
     * @param sizeList 调整图片大小数组
     * @param filePath 文件所在url
     * @param fileName 文件名
     * @throws IOException
     */
    public static void resizeImage(List<Integer> sizeList, String filePath, String fileName) throws IOException {
        for (Integer size : sizeList) {//遍历每个大小
            resizeImage(filePath + fileName, filePath + size + "/" + fileName, size, size);
        }
    }

    /**
     * 调整图片大小
     *
     * @param beforePath 原图片路径
     * @param afterPath  转换大小后图片路径
     * @param width      转换后图片宽度
     * @param height     转换后图片高度
     */
    private static void resizeImage(String beforePath, String afterPath, int width, int height) throws IOException {
        File beforeFile = new File(beforePath);
        File afterFile = new File(afterPath);
        if (!afterFile.exists()) afterFile.mkdirs();//如果文件夹不存在，自动创建
        Image srcImg = ImageIO.read(beforeFile);
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, "PNG", afterFile);
    }

    /**
     * 删除路径对应的文件
     *
     * @param url 文件路径
     */
    public static void deleteFile(String url) {
        File file = new File(url);
        String fileName = file.getName();//取出文件名
        File root = file.getParentFile();//取出父目录
        if (file.exists()) file.delete();//删除文件
        //遍历目录文件夹
        File[] list = root.listFiles();//取出子文件
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) deleteFile(root, fileName);//如果是目录，递归遍历删除同名文件
            }
        }
    }

    /**
     * 递归删除同名文件
     *
     * @param root 递归目录
     * @param name 删除文件名
     */
    private static void deleteFile(File root, String name) {
        File[] list = root.listFiles();//取出子文件
        if (list != null) {
            for (File file : list) {
                if (file.isFile() && file.getName().equals(name)) {//如果是文件，而且同名
                    file.delete();//删除文件
                } else if (file.isDirectory()) {//如果是目录，则继续递归遍历
                    deleteFile(file, name);
                }
            }
        }
    }

}