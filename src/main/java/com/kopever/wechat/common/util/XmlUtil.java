package com.kopever.wechat.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 * Created by Lullaby on 2016/10/20.
 */
public class XmlUtil {

    private static <T> XStream getStream(Class<T> clazz) {
        XStream xstream = new XStream(new StaxDriver());

        XStream.setupDefaultSecurity(xstream);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);

        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xstream.ignoreUnknownElements();

        if (!clazz.isAnnotationPresent(XStreamAlias.class)) {
            xstream.alias("xml", clazz);
        }

//        List<Class> classes = Lists.newArrayList(clazz);
//        while (true) {
//            Class superClass = clazz.getSuperclass();
//            if (superClass != null) {
//                classes.add(superClass);
//                clazz = superClass;
//            } else {
//                break;
//            }
//        }
//        xstream.allowTypes(classes.toArray(new Class[classes.size()]));
        xstream.allowTypes(new Class[]{clazz});
        xstream.allowTypeHierarchy(Collection.class);

        return xstream;
    }

    public static <T> T fromXml(String xml, Class<T> clazz) {
        return clazz.cast(getStream(clazz).fromXML(xml));
    }

//    public static <T> T fromXml(String xml, String root, Class<T> clazz) {
//        XStream xstream = getStream(clazz);
//        xstream.alias(root, clazz);
//
//        return clazz.cast(xstream.fromXML(xml));
//    }

    public static <T> T fromXml(InputStream in, Class<T> clazz) {
        return clazz.cast(getStream(clazz).fromXML(in));
    }

    public static <T> String toXml(Object obj, Class<T> clazz) {
        return getStream(clazz).toXML(obj);
    }

    public static <T> void toXml(Object obj, Class<T> clazz, OutputStream out) {
        getStream(clazz).toXML(obj, out);
    }

}
