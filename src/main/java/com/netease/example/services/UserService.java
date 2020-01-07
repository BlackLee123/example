//package com.netease.example.services;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netease.example.dao.UserDao;
//import com.netease.example.domain.User;
//import org.dom4j.Attribute;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.internet.MimeMessage;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.nio.file.Paths;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static java.nio.file.Files.readAllBytes;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${mail.fromMail.addr}")
//    private String from;
//
//    User getAdminsOfFit() {
//        return userDao.selectByPrimaryKey(1);
//    }
//
//    /**
//     * 发送html邮件
//     *
//     * @param toAddr  接收邮箱
//     * @param title   邮件标题
//     * @param content 邮件内容
//     */
//    public boolean sendHtmlMail(String toAddr, String title, String content) throws javax.mail.MessagingException {
//        // html 邮件对象
//        MimeMessage message = mailSender.createMimeMessage();
//        //true表示需要创建一个multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom(from);
//        helper.setTo(toAddr);
//        helper.setSubject(title);
//        helper.setText(content, true);
//        mailSender.send(message);
//        return true;
//    }
//
//    public String generateContent(String email) throws DocumentException, IOException {
//        String confirmUrl = generateConfirmUrl(email);
//        SAXReader reader = new SAXReader();
//        Document document;
//        //获取模板html文档
//        document = reader.read(this.getClass().getResourceAsStream("/email/content.html"));
//        Element root = document.getRootElement();
//        Element confirmEle = getNodes(root, "id", "confirmUrl");
//        confirmEle.attribute("href").setValue(confirmUrl);
//        Element confirmUrlCheckEle = getNodes(root, "id", "confirmUrlCheck");
//        confirmUrlCheckEle.setText(confirmUrl);
//
//        OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
//        xmlFormat.setEncoding("utf-8");
//        FileWriter fWriter = new FileWriter("tmp.html");
//        XMLWriter writer = new XMLWriter(fWriter, xmlFormat);
//        writer.write(document);
//        writer.flush();
//        writer.close();
//        return new String(readAllBytes(Paths.get("tmp.html")));
//    }
//
//    private String generateConfirmUrl(String email) throws UnsupportedEncodingException, JsonProcessingException {
//        Map<String, String> info = new HashMap<>();
//        info.put("email", email);
//        ObjectMapper mapper = new ObjectMapper();
//        String infoStr = mapper.writeValueAsString(info);
//        infoStr = base64Encode(infoStr);
//        return String.format("http://fit-saas.nie.netease.com:8089/v1/email/verify/%s", infoStr);
//    }
//
//    public String base64Encode(String encodeStr) throws UnsupportedEncodingException {
//        return Base64.getEncoder().encodeToString(encodeStr.getBytes("utf-8"));
//    }
//
//    public String base64Decode(String decodeStr) throws UnsupportedEncodingException {
//        byte[] base64decodedBytes = Base64.getDecoder().decode(decodeStr);
//        return new String(base64decodedBytes, "utf-8");
//    }
//
//    public Map<String, String> getVerifyInfo(String verifyStr) throws IOException {
//        verifyStr = base64Decode(verifyStr);
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(verifyStr, Map.class);
//    }
//
//    /**
//     * 方法描述：递归遍历子节点，根据属性名和属性值，找到对应属性名和属性值的那个子孙节点。
//     *
//     * @param node      要进行子节点遍历的节点
//     * @param attrName  属性名
//     * @param attrValue 属性值
//     * @return 返回对应的节点或null
//     */
//    private Element getNodes(Element node, String attrName, String attrValue) {
//
//        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
//        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
//            final String name = attr.getName();// 属性名称
//            final String value = attr.getValue();// 属性的值
//            if(attrName.equals(name) && attrValue.equals(value)){
//                return node;
//            }
//        }
//        // 递归遍历当前节点所有的子节点
//        final List<Element> listElement = node.elements();// 所有一级子节点的list
//        for (Element e : listElement) {// 遍历所有一级子节点
//            Element temp = getNodes(e, attrName, attrValue);
//            if(temp != null){
//                return temp;
//            }
//        }
//        return null;
//    }
//
//}
