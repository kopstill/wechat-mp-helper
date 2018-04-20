package com.kopever.wechat.common.instance;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.util.Collection;

/**
 * Created by Lullaby on 2018/1/17
 */
public enum XStreamInstance {

    INSTANCE;

    private XStream xstream;

    XStreamInstance() {
        xstream = new XStream(new StaxDriver());

        XStream.setupDefaultSecurity(xstream);

        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypesByWildcard(new String[]{"com.kopever.wechat.domain.**", "com.kopever.wechat.test.domain.*"});
    }

    public XStream getXstream() {
        return xstream;
    }

}
