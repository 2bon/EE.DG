/* Generated By:JavaCC: Do not edit this line. XTFTokenizer.java */
package org.cdlib.xtf.textIndexer.tokenizer;

/** A grammar-based tokenizer constructed with JavaCC.
 *
 * <p> This should be a good tokenizer for most European-language documents.
 *
 * <p>Many applications have specific tokenizer needs.  If this tokenizer does
 * not suit your application, please consider copying this source code
 * directory to your project and maintaining your own grammar-based tokenizer.
 */
public class XTFTokenizer extends org.apache.lucene.analysis.Tokenizer implements XTFTokenizerConstants {

  /** Constructs a tokenizer for this Reader. */
  public XTFTokenizer(java.io.Reader reader) {
    this(new FastCharStream(reader));
    this.input = reader;
  }

/** Returns the next token in the stream, or null at EOS.
 * <p>The returned token's type is set to an element of {@link
 * StandardTokenizerConstants#tokenImage}.
 */
  @SuppressWarnings("unused")
  final public org.apache.lucene.analysis.Token next() throws ParseException, java.io.IOException {
  Token token = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BASIC:
      token = jj_consume_token(BASIC);
      break;
    case APOSTROPHE:
      token = jj_consume_token(APOSTROPHE);
      break;
    case ACRONYM:
      token = jj_consume_token(ACRONYM);
      break;
    case COMPANY:
      token = jj_consume_token(COMPANY);
      break;
    case EMAIL:
      token = jj_consume_token(EMAIL);
      break;
    case HOST:
      token = jj_consume_token(HOST);
      break;
    case NUM:
      token = jj_consume_token(NUM);
      break;
    case SYMBOL:
      token = jj_consume_token(SYMBOL);
      break;
    case CJK:
      token = jj_consume_token(CJK);
      break;
    case 0:
      token = jj_consume_token(0);
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      if (token.kind == EOF) {
        {if (true) return null;}
      } else {
        {if (true) return
          new org.apache.lucene.analysis.Token(token.image,
                                        token.beginColumn,token.endColumn,
                                        tokenImage[token.kind]);}
      }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public XTFTokenizerTokenManager token_source;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[1];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x41ff,};
   }

  /** Constructor with user supplied CharStream. */
  public XTFTokenizer(CharStream stream) {
    token_source = new XTFTokenizerTokenManager(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(CharStream stream) {
    token_source.ReInit(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public XTFTokenizer(XTFTokenizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(XTFTokenizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 1; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 1; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
