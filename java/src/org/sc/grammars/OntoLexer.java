// $ANTLR 3.3 Nov 30, 2010 12:50:56 Onto.g 2010-12-16 11:19:21
 
package org.sc.grammars;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class OntoLexer extends Lexer {
    public static final int EOF=-1;
    public static final int DEFINE=4;
    public static final int ASSERT=5;
    public static final int TRIPLES=6;
    public static final int SOME=7;
    public static final int ONLY=8;
    public static final int VALUE=9;
    public static final int WHERE=10;
    public static final int TABLE=11;
    public static final int ALIAS=12;
    public static final int PRFX=13;
    public static final int IMPORT=14;
    public static final int COLON=15;
    public static final int TAB=16;
    public static final int QUOTE=17;
    public static final int DOLLAR=18;
    public static final int AT=19;
    public static final int WS=20;
    public static final int LITERAL=21;
    public static final int VAR=22;
    public static final int QUOTED=23;
    public static final int PREFIXED=24;
    public static final int NEWLINE=25;
    public static final int PREFIX=26;
    public static final int LETTER=27;
    public static final int DIGIT=28;

    // delegates
    // delegators

    public OntoLexer() {;} 
    public OntoLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public OntoLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Onto.g"; }

    // $ANTLR start "DEFINE"
    public final void mDEFINE() throws RecognitionException {
        try {
            int _type = DEFINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:7:8: ( 'define' )
            // Onto.g:7:10: 'define'
            {
            match("define"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DEFINE"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:8:8: ( 'assert' )
            // Onto.g:8:10: 'assert'
            {
            match("assert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "TRIPLES"
    public final void mTRIPLES() throws RecognitionException {
        try {
            int _type = TRIPLES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:9:9: ( 'triples' )
            // Onto.g:9:11: 'triples'
            {
            match("triples"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRIPLES"

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:10:6: ( 'some' )
            // Onto.g:10:8: 'some'
            {
            match("some"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOME"

    // $ANTLR start "ONLY"
    public final void mONLY() throws RecognitionException {
        try {
            int _type = ONLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:11:6: ( 'only' )
            // Onto.g:11:8: 'only'
            {
            match("only"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ONLY"

    // $ANTLR start "VALUE"
    public final void mVALUE() throws RecognitionException {
        try {
            int _type = VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:12:7: ( 'value' )
            // Onto.g:12:9: 'value'
            {
            match("value"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUE"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:13:7: ( 'where' )
            // Onto.g:13:9: 'where'
            {
            match("where"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "TABLE"
    public final void mTABLE() throws RecognitionException {
        try {
            int _type = TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:14:7: ( 'table' )
            // Onto.g:14:9: 'table'
            {
            match("table"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TABLE"

    // $ANTLR start "ALIAS"
    public final void mALIAS() throws RecognitionException {
        try {
            int _type = ALIAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:15:7: ( 'alias' )
            // Onto.g:15:9: 'alias'
            {
            match("alias"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALIAS"

    // $ANTLR start "PRFX"
    public final void mPRFX() throws RecognitionException {
        try {
            int _type = PRFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:16:6: ( 'prefix' )
            // Onto.g:16:8: 'prefix'
            {
            match("prefix"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRFX"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:17:8: ( 'import' )
            // Onto.g:17:10: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:18:7: ( ':' )
            // Onto.g:18:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "TAB"
    public final void mTAB() throws RecognitionException {
        try {
            int _type = TAB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:19:5: ( '\\t' )
            // Onto.g:19:7: '\\t'
            {
            match('\t'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TAB"

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:20:7: ( '\\'' )
            // Onto.g:20:9: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:21:8: ( '$' )
            // Onto.g:21:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:22:4: ( '@' )
            // Onto.g:22:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "PREFIXED"
    public final void mPREFIXED() throws RecognitionException {
        try {
            int _type = PREFIXED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:77:10: ( PREFIX LITERAL )
            // Onto.g:77:12: PREFIX LITERAL
            {
            mPREFIX(); 
            mLITERAL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PREFIXED"

    // $ANTLR start "PREFIX"
    public final void mPREFIX() throws RecognitionException {
        try {
            int _type = PREFIX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:78:8: ( LETTER ( LETTER | DIGIT )* COLON )
            // Onto.g:78:10: LETTER ( LETTER | DIGIT )* COLON
            {
            mLETTER(); 
            // Onto.g:78:17: ( LETTER | DIGIT )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Onto.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            mCOLON(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PREFIX"

    // $ANTLR start "LITERAL"
    public final void mLITERAL() throws RecognitionException {
        try {
            int _type = LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:80:9: ( ( LETTER | '_' | '-' | '.' )+ )
            // Onto.g:80:11: ( LETTER | '_' | '-' | '.' )+
            {
            // Onto.g:80:11: ( LETTER | '_' | '-' | '.' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='-' && LA2_0<='.')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Onto.g:
            	    {
            	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LITERAL"

    // $ANTLR start "QUOTED"
    public final void mQUOTED() throws RecognitionException {
        try {
            int _type = QUOTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:82:8: ( QUOTE (~ QUOTE )* QUOTE )
            // Onto.g:82:10: QUOTE (~ QUOTE )* QUOTE
            {
            mQUOTE(); 
            // Onto.g:82:16: (~ QUOTE )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\u0000' && LA3_0<='&')||(LA3_0>='(' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Onto.g:82:17: ~ QUOTE
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\u0010')||(input.LA(1)>='\u0012' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            mQUOTE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUOTED"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:84:5: ( DOLLAR LETTER ( LETTER | DIGIT )* | AT LETTER ( LETTER | DIGIT )* )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='$') ) {
                alt6=1;
            }
            else if ( (LA6_0=='@') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // Onto.g:84:7: DOLLAR LETTER ( LETTER | DIGIT )*
                    {
                    mDOLLAR(); 
                    mLETTER(); 
                    // Onto.g:84:21: ( LETTER | DIGIT )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||(LA4_0>='a' && LA4_0<='z')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // Onto.g:
                    	    {
                    	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Onto.g:85:7: AT LETTER ( LETTER | DIGIT )*
                    {
                    mAT(); 
                    mLETTER(); 
                    // Onto.g:85:17: ( LETTER | DIGIT )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // Onto.g:
                    	    {
                    	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // Onto.g:88:16: ( '0' .. '9' )
            // Onto.g:88:18: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // Onto.g:89:17: ( 'a' .. 'z' | 'A' .. 'Z' )
            // Onto.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:91:9: ( '\\n' )
            // Onto.g:91:11: '\\n'
            {
            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Onto.g:92:4: ( ( ' ' | '\\u000C' )+ )
            // Onto.g:92:6: ( ' ' | '\\u000C' )+
            {
            // Onto.g:92:6: ( ' ' | '\\u000C' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\f'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Onto.g:
            	    {
            	    if ( input.LA(1)=='\f'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

             

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // Onto.g:1:8: ( DEFINE | ASSERT | TRIPLES | SOME | ONLY | VALUE | WHERE | TABLE | ALIAS | PRFX | IMPORT | COLON | TAB | QUOTE | DOLLAR | AT | PREFIXED | PREFIX | LITERAL | QUOTED | VAR | NEWLINE | WS )
        int alt8=23;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // Onto.g:1:10: DEFINE
                {
                mDEFINE(); 

                }
                break;
            case 2 :
                // Onto.g:1:17: ASSERT
                {
                mASSERT(); 

                }
                break;
            case 3 :
                // Onto.g:1:24: TRIPLES
                {
                mTRIPLES(); 

                }
                break;
            case 4 :
                // Onto.g:1:32: SOME
                {
                mSOME(); 

                }
                break;
            case 5 :
                // Onto.g:1:37: ONLY
                {
                mONLY(); 

                }
                break;
            case 6 :
                // Onto.g:1:42: VALUE
                {
                mVALUE(); 

                }
                break;
            case 7 :
                // Onto.g:1:48: WHERE
                {
                mWHERE(); 

                }
                break;
            case 8 :
                // Onto.g:1:54: TABLE
                {
                mTABLE(); 

                }
                break;
            case 9 :
                // Onto.g:1:60: ALIAS
                {
                mALIAS(); 

                }
                break;
            case 10 :
                // Onto.g:1:66: PRFX
                {
                mPRFX(); 

                }
                break;
            case 11 :
                // Onto.g:1:71: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 12 :
                // Onto.g:1:78: COLON
                {
                mCOLON(); 

                }
                break;
            case 13 :
                // Onto.g:1:84: TAB
                {
                mTAB(); 

                }
                break;
            case 14 :
                // Onto.g:1:88: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 15 :
                // Onto.g:1:94: DOLLAR
                {
                mDOLLAR(); 

                }
                break;
            case 16 :
                // Onto.g:1:101: AT
                {
                mAT(); 

                }
                break;
            case 17 :
                // Onto.g:1:104: PREFIXED
                {
                mPREFIXED(); 

                }
                break;
            case 18 :
                // Onto.g:1:113: PREFIX
                {
                mPREFIX(); 

                }
                break;
            case 19 :
                // Onto.g:1:120: LITERAL
                {
                mLITERAL(); 

                }
                break;
            case 20 :
                // Onto.g:1:128: QUOTED
                {
                mQUOTED(); 

                }
                break;
            case 21 :
                // Onto.g:1:135: VAR
                {
                mVAR(); 

                }
                break;
            case 22 :
                // Onto.g:1:139: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 23 :
                // Onto.g:1:147: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\11\20\2\uffff\1\41\1\43\1\45\1\20\3\uffff\2\20\1\47\1\uffff"+
        "\12\20\5\uffff\1\20\2\uffff\17\20\1\103\1\104\6\20\1\113\1\20\1"+
        "\115\2\uffff\1\116\1\117\2\20\1\122\1\123\1\uffff\1\20\3\uffff\1"+
        "\125\1\126\2\uffff\1\127\3\uffff";
    static final String DFA8_eofS =
        "\130\uffff";
    static final String DFA8_minS =
        "\1\11\11\60\2\uffff\1\0\2\101\1\60\3\uffff\2\60\1\55\13\60\5\uffff"+
        "\1\60\2\uffff\17\60\2\55\6\60\1\55\1\60\1\55\2\uffff\2\55\2\60\2"+
        "\55\1\uffff\1\60\3\uffff\2\55\2\uffff\1\55\3\uffff";
    static final String DFA8_maxS =
        "\12\172\2\uffff\1\uffff\3\172\3\uffff\16\172\5\uffff\1\172\2\uffff"+
        "\32\172\2\uffff\6\172\1\uffff\1\172\3\uffff\2\172\2\uffff\1\172"+
        "\3\uffff";
    static final String DFA8_acceptS =
        "\12\uffff\1\14\1\15\4\uffff\1\23\1\26\1\27\16\uffff\1\16\1\24\1"+
        "\17\1\25\1\20\1\uffff\1\22\1\21\32\uffff\1\4\1\5\6\uffff\1\11\1"+
        "\uffff\1\10\1\6\1\7\2\uffff\1\1\1\2\1\uffff\1\12\1\13\1\3";
    static final String DFA8_specialS =
        "\14\uffff\1\0\113\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\13\1\21\1\uffff\1\22\23\uffff\1\22\3\uffff\1\15\2\uffff\1"+
            "\14\5\uffff\2\20\13\uffff\1\12\5\uffff\1\16\32\17\4\uffff\1"+
            "\20\1\uffff\1\2\2\17\1\1\4\17\1\11\5\17\1\5\1\10\2\17\1\4\1"+
            "\3\1\17\1\6\1\7\3\17",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\23\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\13\24\1\30\6\24\1\27\7\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\1\32\20\24\1\31\10\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\16\24\1\33\13\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\15\24\1\34\14\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\1\35\31\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\7\24\1\36\22\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\21\24\1\37\10\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\14\24\1\40\15\24",
            "",
            "",
            "\0\42",
            "\32\44\6\uffff\32\44",
            "\32\44\6\uffff\32\44",
            "\12\26\1\25\6\uffff\32\24\6\uffff\32\24",
            "",
            "",
            "",
            "\12\26\1\25\6\uffff\32\24\6\uffff\5\24\1\46\24\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\32\24",
            "\2\50\22\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\26\1\25\6\uffff\32\26\6\uffff\32\26",
            "\12\26\1\25\6\uffff\32\24\6\uffff\22\24\1\51\7\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\10\24\1\52\21\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\10\24\1\53\21\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\1\24\1\54\30\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\14\24\1\55\15\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\13\24\1\56\16\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\13\24\1\57\16\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\60\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\61\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\17\24\1\62\12\24",
            "",
            "",
            "",
            "",
            "",
            "\12\26\1\25\6\uffff\32\24\6\uffff\10\24\1\63\21\24",
            "",
            "",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\64\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\1\65\31\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\17\24\1\66\12\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\13\24\1\67\16\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\70\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\30\24\1\71\1\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\24\24\1\72\5\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\21\24\1\73\10\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\5\24\1\74\24\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\16\24\1\75\13\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\15\24\1\76\14\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\21\24\1\77\10\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\22\24\1\100\7\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\13\24\1\101\16\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\102\25\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\105\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\106\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\10\24\1\107\21\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\21\24\1\110\10\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\111\25\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\23\24\1\112\6\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\4\24\1\114\25\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "",
            "",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\27\24\1\120\2\24",
            "\12\26\1\25\6\uffff\32\24\6\uffff\23\24\1\121\6\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "",
            "\12\26\1\25\6\uffff\32\24\6\uffff\22\24\1\124\7\24",
            "",
            "",
            "",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "",
            "",
            "\2\20\1\uffff\12\26\1\25\6\uffff\32\24\4\uffff\1\20\1\uffff"+
            "\32\24",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( DEFINE | ASSERT | TRIPLES | SOME | ONLY | VALUE | WHERE | TABLE | ALIAS | PRFX | IMPORT | COLON | TAB | QUOTE | DOLLAR | AT | PREFIXED | PREFIX | LITERAL | QUOTED | VAR | NEWLINE | WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_12 = input.LA(1);

                        s = -1;
                        if ( ((LA8_12>='\u0000' && LA8_12<='\uFFFF')) ) {s = 34;}

                        else s = 33;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}