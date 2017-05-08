package com.library.app.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

/**
 * Created by BRUNO-PC on 02/05/2017.
 */
public class AnnotationProcesser {
    public static void main(String[] args) throws Exception
    {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.library.app.utils" + ".KeepBasePackage");
        // Without the call to "makePackage()", package information is lost
        cp.makePackage(cp.getClassLoader(), "com.library.app.utils");
        addAnnotation(cc, "porraToda", "java.lang.Deprecated");
        // Changes are not persisted without a call to "toClass()"
        Class<?> c = cc.toClass();

        Field fld = KeepBasePackage.class.getDeclaredField("porraToda");
        System.out.println(c.getPackage());
        // System.out.println(fld.getAnnotation(Deprecated.class));
        imprimirAnotacao();
    }

    public static void addAnnotation(
            CtClass clazz,
            String fieldName,
            String annotationName) throws Exception
    {

        ClassFile cfile = clazz.getClassFile();
        ConstPool cpool = cfile.getConstPool();
        CtField cfield  = clazz.getField(fieldName);

        AnnotationsAttribute attr =
                new AnnotationsAttribute(cpool, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation(annotationName, cpool);
        attr.addAnnotation(annot);
        cfield.getFieldInfo().addAttribute(attr);
    }

    private static void imprimirAnotacao() throws IOException {

        com.google.common.reflect.ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses();

        Field fld = null;
        try {
            fld = KeepBasePackage.class.getDeclaredField("porraToda");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(fld.getAnnotation(Deprecated.class));

        Package.getPackage("com.library.app.utils");

    }

}
