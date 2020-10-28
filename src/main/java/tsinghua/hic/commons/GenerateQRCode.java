package tsinghua.hic.commons;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import tsinghua.hic.service.impl.HashGenerateServiceImpl;

public class GenerateQRCode {
    private static final Logger logger = LoggerFactory
            .getLogger(HashGenerateServiceImpl.class);

    public String QREncode(int width, int height, String content, String gid)
            throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        logger.info(content);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(
                Color.BLACK.getRGB(), Color.WHITE.getRGB());
        File file = new File("img/" + gid + ".jpg");
        MatrixToImageWriter.writeToPath(bitMatrix, "jpg", file.toPath(),
                matrixToImageConfig);
        logger.info(content);
        return gid + ".jpg";
    }
}