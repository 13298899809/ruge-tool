package com.ruge.core.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 根据参数(GET或POST参数)列表和SecretKey生成签名，该签名就是URL中的sign参数
 *
 * @author: mike.ma
 * @Date: 2014/6/12
 */
public class SignatureGenerator {
    /**
     * 根据Url（资源部分），url参数列表和SecretKey生成签名
     *
     * @param urlResourcePart URL资源部分，例如有如下URL：http://faw-vw.timanetwork.com/access/tm/user/getUserInfo?apramX=valueX, 其资源部分是"user/getUserInfo"
     * @param params
     * @param secretKey
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String generate(String urlResourcePart, Map<String, String> params, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //对参数按名称排序(升序)
        List<Map.Entry<String, String>> parameters = new LinkedList<>(params.entrySet());
        parameters.sort(Map.Entry.comparingByKey());

        //形成参数字符串, 并把SecretKey加在末尾（salt）
        StringBuilder sb = new StringBuilder();
        sb.append(urlResourcePart).append("_");
        for (Map.Entry<String, String> param : parameters) {
            sb.append(param.getKey()).append("=").append(param.getValue()).append("_");
        }
        sb.append(secretKey);

        System.out.println(sb.toString());
        String baseString = URLEncoder.encode(sb.toString(), "UTF-8");
        System.out.println(baseString);
        return MD5Util.md5(baseString);
    }

    /**
     * {{server}}driving-report/internal/driving/monitor/electricity?appkey={{appkey}}&sign={{sign}}&signt={{signt}}&nonce={{nonce}}
     * <p>
     * http://172.20.2.160:10007/internal/driving/monitor/electricity?appkey=2368392532&sign=6532e381b279bb0202de6ee07af26bf6&signt=1598231312581&nonce=7d286bb7a55122410088c557f0bb2ec93e54
     * <p>
     * <p>
     * 拆分后的url地址
     * <p>
     * http://172.20.2.160:10007
     * <p>
     * /internal/driving/monitor/electricity
     * ?
     * appkey=2368392532
     * &
     * sign=6532e381b279bb0202de6ee07af26bf6
     * &
     * signt=1598231312581
     * &
     * nonce=7d286bb7a55122410088c557f0bb2ec93e54
     *
     *
     * http://op-test.arcfox.timanetwork.net/
     * driving-report/internal/driving/monitor/electricity
     * ?
     * appkey=2368392532
     * &
     * sign=de7f321463217b960e6ef59147a97fb0
     * &
     * signt=1598257623356
     * &
     * nonce=7d286bb7a55122410088c557f0bb2ec93e54
     *
     * @param args
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> params = new HashMap<>(8);
        params.put("appkey", "2368392532");
        params.put("signt", "1598257623356");
        params.put("nonce", "7d286bb7a55122410088c557f0bb2ec93e54");
        String urlResourcePart = "driving-report/internal/driving/monitor/electricity";

        String sign = generate(urlResourcePart, params, "26abfc143bfe3cda422d91e4c9ca4783");
        System.out.println(sign);
    }
}
