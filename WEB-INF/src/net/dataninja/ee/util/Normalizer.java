package net.dataninja.ee.util;

/**
dataninja copyright statement
 */

import java.lang.reflect.Method;

/**
 * Handles Unicode normalization, dynamically choosing whichever of the built-in
 * Java classes is available to do the work (these changed between Java 1.5 and
 * Java 1.6).
 * 
 * @author Rick Li
 */
public class Normalizer
{
//  /** This will do the actual work, depending on the platform * 
  private static PlatformNormalizer platformNormalizer = null;

  /**
   * Perform normalization on a string, meaning canonical decomposition
   * followed by canonical composition.
   */
  public static String normalize(String in)
  {
    // If the word doesn't have any unusual chars, we can skip the slow
    // process of normalizing it.
    //
    boolean allSafe = true;
    for (int i=0; i<in.length(); i++) {
      if ((in.charAt(i) & ~0x7F) != 0)
        allSafe = false;
    }
    
    if (allSafe)
      return in;

    // Load platform-specific normalization code (differs between JDK 1.5 and 1.6)
    if (platformNormalizer == null) {
      try {
        platformNormalizer = new Jdk16Normalizer();
      }
      catch (Exception e) {
        try {
          platformNormalizer = new Jdk15Normalizer();
        }
        catch (Exception e2) {
          throw new RuntimeException(e2);
        }
      }
    }

    // And go for it.
    return platformNormalizer.normalize(in);
  }

  /** Generic interface for normalizers */
  private interface PlatformNormalizer
  {
    String normalize(String in);
  }

  /** Normalizer that runs on JDK 1.6 / 6.0 and higher */
  private static class Jdk16Normalizer implements PlatformNormalizer
  {
    private Method method;
    private Object form;

    /** Constructor - use Java Reflection to locate the class and method */
    public Jdk16Normalizer() throws Exception
    {
      Class normalizerClass = Class.forName("java.text.Normalizer");
      Class formClass = Class.forName("java.text.Normalizer$Form");

      method = normalizerClass.getMethod("normalize", new Class[] {
          CharSequence.class, formClass });

      form = formClass.getField("NFC").get(null);
    }

    /** Normalize a string using the method we found */
    public String normalize(String in)
    {
      try {
        return (String) method.invoke(null, new Object[] { (CharSequence)in, form });
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  /** Normalizer that runs on JDK 1.5 / 5.0 */
  private static class Jdk15Normalizer implements PlatformNormalizer
  {
    private Method method;
    private Object mode;

    /** Constructor - use Java Reflection to locate the class and method */
    public Jdk15Normalizer() throws Exception
    {
      Class normalizerClass = Class.forName("sun.text.Normalizer");
      Class modeClass = Class.forName("sun.text.Normalizer$Mode");

      method = normalizerClass.getMethod("normalize", new Class[] {
          String.class, modeClass, int.class });

      mode = normalizerClass.getField("COMPOSE").get(null);
    }

    /** Normalize a string using the method we found */
    public String normalize(String in)
    {
      try {
        return (String) method.invoke(null, new Object[] { in, mode, 0 });
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
