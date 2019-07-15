package com.onexzgj.onexlibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * author：lubodada
 * data：2019/3/27
 */

public class SqlDaoUtil {

    // 项目根文件夹
    public static final String PROJECT_ROOT_DIR = Environment.getExternalStorageDirectory() + "/databases/";
    // 数据库文件
    public static final String DB_FILE_PATH = PROJECT_ROOT_DIR + "SWID_GKPT.db";


    /***
     * 将assets下的文件复制到sd卡
     *
     * @param pContext
     * @param pAssetsFilePath -assets下的文件路径
     * @param pSDCardFilePath -sd卡文件路径
     * @return 是否成功
     */
    public static boolean copyAssetsToSDCard(Context pContext, String pAssetsFilePath, String pSDCardFilePath) {

        File file = new File(separatorReplace(pSDCardFilePath));
        if (file.exists()) {
            return true;
        }

        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            file.getParentFile().mkdirs();
        }
        // 创建目标文件
        try {
            AssetManager am = pContext.getAssets();
            InputStream inputStream = am.open(pAssetsFilePath);
            OutputStream outputStream = new FileOutputStream(file);
            // 文件写入
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            // 关闭文件流
            outputStream.close();
            inputStream.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /***
     * 处理文件路径
     *
     * @param path
     * @return
     */
    public static String separatorReplace(String path) {
        return path.replace("\\", "/");
    }


}
