// $ANTLR 3.3 Nov 30, 2010 12:50:56 Onto.g 2010-12-16 11:19:21
 
package org.sc.grammars;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;

import org.antlr.runtime.tree.*;

public class OntoParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DEFINE", "ASSERT", "TRIPLES", "SOME", "ONLY", "VALUE", "WHERE", "TABLE", "ALIAS", "PRFX", "IMPORT", "COLON", "TAB", "QUOTE", "DOLLAR", "AT", "WS", "LITERAL", "VAR", "QUOTED", "PREFIXED", "NEWLINE", "PREFIX", "LETTER", "DIGIT"
    };
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

    public static final String[] ruleNames = new String[] {
        "invalidRule", "innerline", "triples", "assertline", "defineline", 
        "triple_entry", "prefixline", "tableline", "template", "classline", 
        "singleline", "restrictline", "headline", "eol", "statement", "name", 
        "importline"
    };
    public static final boolean[] decisionCanBacktrack = new boolean[] {
        false, // invalid decision
        false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false
    };

     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public OntoParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public OntoParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this,port,adaptor);
            setDebugListener(proxy);
            setTokenStream(new DebugTokenStream(input,proxy));
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
            TreeAdaptor adap = new CommonTreeAdaptor();
            setTreeAdaptor(adap);
            proxy.setTreeAdaptor(adap);
        }
    public OntoParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg);

         
        TreeAdaptor adap = new CommonTreeAdaptor();
        setTreeAdaptor(adap);

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }

    protected DebugTreeAdaptor adaptor;
    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = new DebugTreeAdaptor(dbg,adaptor);

    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }


    public String[] getTokenNames() { return OntoParser.tokenNames; }
    public String getGrammarFileName() { return "Onto.g"; }


    public static class template_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "template"
    // Onto.g:34:1: template : ( eol )? ( statement )* EOF ;
    public final OntoParser.template_return template() throws RecognitionException {
        OntoParser.template_return retval = new OntoParser.template_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF3=null;
        OntoParser.eol_return eol1 = null;

        OntoParser.statement_return statement2 = null;


        Object EOF3_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "template");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(34, 1);

        try {
            // Onto.g:34:10: ( ( eol )? ( statement )* EOF )
            dbg.enterAlt(1);

            // Onto.g:34:12: ( eol )? ( statement )* EOF
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(34,12);
            // Onto.g:34:12: ( eol )?
            int alt1=2;
            try { dbg.enterSubRule(1);
            try { dbg.enterDecision(1, decisionCanBacktrack[1]);

            int LA1_0 = input.LA(1);

            if ( (LA1_0==WS||LA1_0==NEWLINE) ) {
                alt1=1;
            }
            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:34:12: eol
                    {
                    dbg.location(34,12);
                    pushFollow(FOLLOW_eol_in_template214);
                    eol1=eol();

                    state._fsp--;

                    adaptor.addChild(root_0, eol1.getTree());

                    }
                    break;

            }
            } finally {dbg.exitSubRule(1);}

            dbg.location(34,17);
            // Onto.g:34:17: ( statement )*
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2, decisionCanBacktrack[2]);

                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=DEFINE && LA2_0<=ASSERT)||LA2_0==TABLE||(LA2_0>=PRFX && LA2_0<=IMPORT)) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // Onto.g:34:17: statement
            	    {
            	    dbg.location(34,17);
            	    pushFollow(FOLLOW_statement_in_template217);
            	    statement2=statement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement2.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);
            } finally {dbg.exitSubRule(2);}

            dbg.location(34,28);
            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_template220); 
            EOF3_tree = (Object)adaptor.create(EOF3);
            adaptor.addChild(root_0, EOF3_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(34, 32);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "template");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "template"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // Onto.g:36:1: statement : ( headline eol ( triples )? ( ( TAB )+ innerline eol )* | singleline eol );
    public final OntoParser.statement_return statement() throws RecognitionException {
        OntoParser.statement_return retval = new OntoParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TAB7=null;
        OntoParser.headline_return headline4 = null;

        OntoParser.eol_return eol5 = null;

        OntoParser.triples_return triples6 = null;

        OntoParser.innerline_return innerline8 = null;

        OntoParser.eol_return eol9 = null;

        OntoParser.singleline_return singleline10 = null;

        OntoParser.eol_return eol11 = null;


        Object TAB7_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "statement");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(36, 1);

        try {
            // Onto.g:37:5: ( headline eol ( triples )? ( ( TAB )+ innerline eol )* | singleline eol )
            int alt6=2;
            try { dbg.enterDecision(6, decisionCanBacktrack[6]);

            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=DEFINE && LA6_0<=ASSERT)||LA6_0==TABLE) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=PRFX && LA6_0<=IMPORT)) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:37:7: headline eol ( triples )? ( ( TAB )+ innerline eol )*
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(37,7);
                    pushFollow(FOLLOW_headline_in_statement235);
                    headline4=headline();

                    state._fsp--;

                    adaptor.addChild(root_0, headline4.getTree());
                    dbg.location(37,16);
                    pushFollow(FOLLOW_eol_in_statement237);
                    eol5=eol();

                    state._fsp--;

                    adaptor.addChild(root_0, eol5.getTree());
                    dbg.location(37,20);
                    // Onto.g:37:20: ( triples )?
                    int alt3=2;
                    try { dbg.enterSubRule(3);
                    try { dbg.enterDecision(3, decisionCanBacktrack[3]);

                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==TAB) ) {
                        int LA3_1 = input.LA(2);

                        if ( (LA3_1==TRIPLES) ) {
                            alt3=1;
                        }
                    }
                    } finally {dbg.exitDecision(3);}

                    switch (alt3) {
                        case 1 :
                            dbg.enterAlt(1);

                            // Onto.g:37:20: triples
                            {
                            dbg.location(37,20);
                            pushFollow(FOLLOW_triples_in_statement239);
                            triples6=triples();

                            state._fsp--;

                            adaptor.addChild(root_0, triples6.getTree());

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(3);}

                    dbg.location(37,29);
                    // Onto.g:37:29: ( ( TAB )+ innerline eol )*
                    try { dbg.enterSubRule(5);

                    loop5:
                    do {
                        int alt5=2;
                        try { dbg.enterDecision(5, decisionCanBacktrack[5]);

                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==TAB) ) {
                            alt5=1;
                        }


                        } finally {dbg.exitDecision(5);}

                        switch (alt5) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // Onto.g:37:30: ( TAB )+ innerline eol
                    	    {
                    	    dbg.location(37,30);
                    	    // Onto.g:37:30: ( TAB )+
                    	    int cnt4=0;
                    	    try { dbg.enterSubRule(4);

                    	    loop4:
                    	    do {
                    	        int alt4=2;
                    	        try { dbg.enterDecision(4, decisionCanBacktrack[4]);

                    	        int LA4_0 = input.LA(1);

                    	        if ( (LA4_0==TAB) ) {
                    	            alt4=1;
                    	        }


                    	        } finally {dbg.exitDecision(4);}

                    	        switch (alt4) {
                    	    	case 1 :
                    	    	    dbg.enterAlt(1);

                    	    	    // Onto.g:37:30: TAB
                    	    	    {
                    	    	    dbg.location(37,30);
                    	    	    TAB7=(Token)match(input,TAB,FOLLOW_TAB_in_statement243); 
                    	    	    TAB7_tree = (Object)adaptor.create(TAB7);
                    	    	    adaptor.addChild(root_0, TAB7_tree);


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    if ( cnt4 >= 1 ) break loop4;
                    	                EarlyExitException eee =
                    	                    new EarlyExitException(4, input);
                    	                dbg.recognitionException(eee);

                    	                throw eee;
                    	        }
                    	        cnt4++;
                    	    } while (true);
                    	    } finally {dbg.exitSubRule(4);}

                    	    dbg.location(37,35);
                    	    pushFollow(FOLLOW_innerline_in_statement246);
                    	    innerline8=innerline();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, innerline8.getTree());
                    	    dbg.location(37,45);
                    	    pushFollow(FOLLOW_eol_in_statement248);
                    	    eol9=eol();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, eol9.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(5);}


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // Onto.g:38:7: singleline eol
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(38,7);
                    pushFollow(FOLLOW_singleline_in_statement259);
                    singleline10=singleline();

                    state._fsp--;

                    adaptor.addChild(root_0, singleline10.getTree());
                    dbg.location(38,18);
                    pushFollow(FOLLOW_eol_in_statement261);
                    eol11=eol();

                    state._fsp--;

                    adaptor.addChild(root_0, eol11.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(39, 5);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "statement");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "statement"

    public static class triples_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "triples"
    // Onto.g:41:1: triples : TAB TRIPLES eol ( TAB TAB triple_entry eol )+ ;
    public final OntoParser.triples_return triples() throws RecognitionException {
        OntoParser.triples_return retval = new OntoParser.triples_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TAB12=null;
        Token TRIPLES13=null;
        Token TAB15=null;
        Token TAB16=null;
        OntoParser.eol_return eol14 = null;

        OntoParser.triple_entry_return triple_entry17 = null;

        OntoParser.eol_return eol18 = null;


        Object TAB12_tree=null;
        Object TRIPLES13_tree=null;
        Object TAB15_tree=null;
        Object TAB16_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "triples");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(41, 1);

        try {
            // Onto.g:42:2: ( TAB TRIPLES eol ( TAB TAB triple_entry eol )+ )
            dbg.enterAlt(1);

            // Onto.g:42:4: TAB TRIPLES eol ( TAB TAB triple_entry eol )+
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(42,4);
            TAB12=(Token)match(input,TAB,FOLLOW_TAB_in_triples277); 
            TAB12_tree = (Object)adaptor.create(TAB12);
            adaptor.addChild(root_0, TAB12_tree);

            dbg.location(42,8);
            TRIPLES13=(Token)match(input,TRIPLES,FOLLOW_TRIPLES_in_triples279); 
            TRIPLES13_tree = (Object)adaptor.create(TRIPLES13);
            adaptor.addChild(root_0, TRIPLES13_tree);

            dbg.location(42,16);
            pushFollow(FOLLOW_eol_in_triples281);
            eol14=eol();

            state._fsp--;

            adaptor.addChild(root_0, eol14.getTree());
            dbg.location(42,20);
            // Onto.g:42:20: ( TAB TAB triple_entry eol )+
            int cnt7=0;
            try { dbg.enterSubRule(7);

            loop7:
            do {
                int alt7=2;
                try { dbg.enterDecision(7, decisionCanBacktrack[7]);

                int LA7_0 = input.LA(1);

                if ( (LA7_0==TAB) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==TAB) ) {
                        int LA7_3 = input.LA(3);

                        if ( ((LA7_3>=LITERAL && LA7_3<=PREFIXED)) ) {
                            int LA7_4 = input.LA(4);

                            if ( (LA7_4==WS) ) {
                                int LA7_5 = input.LA(5);

                                if ( ((LA7_5>=LITERAL && LA7_5<=PREFIXED)) ) {
                                    alt7=1;
                                }


                            }


                        }


                    }


                }


                } finally {dbg.exitDecision(7);}

                switch (alt7) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // Onto.g:42:21: TAB TAB triple_entry eol
            	    {
            	    dbg.location(42,21);
            	    TAB15=(Token)match(input,TAB,FOLLOW_TAB_in_triples284); 
            	    TAB15_tree = (Object)adaptor.create(TAB15);
            	    adaptor.addChild(root_0, TAB15_tree);

            	    dbg.location(42,25);
            	    TAB16=(Token)match(input,TAB,FOLLOW_TAB_in_triples286); 
            	    TAB16_tree = (Object)adaptor.create(TAB16);
            	    adaptor.addChild(root_0, TAB16_tree);

            	    dbg.location(42,29);
            	    pushFollow(FOLLOW_triple_entry_in_triples288);
            	    triple_entry17=triple_entry();

            	    state._fsp--;

            	    adaptor.addChild(root_0, triple_entry17.getTree());
            	    dbg.location(42,42);
            	    pushFollow(FOLLOW_eol_in_triples290);
            	    eol18=eol();

            	    state._fsp--;

            	    adaptor.addChild(root_0, eol18.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt7++;
            } while (true);
            } finally {dbg.exitSubRule(7);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(42, 48);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "triples");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "triples"

    public static class triple_entry_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "triple_entry"
    // Onto.g:44:1: triple_entry : name WS name WS name ;
    public final OntoParser.triple_entry_return triple_entry() throws RecognitionException {
        OntoParser.triple_entry_return retval = new OntoParser.triple_entry_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS20=null;
        Token WS22=null;
        OntoParser.name_return name19 = null;

        OntoParser.name_return name21 = null;

        OntoParser.name_return name23 = null;


        Object WS20_tree=null;
        Object WS22_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "triple_entry");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(44, 1);

        try {
            // Onto.g:44:14: ( name WS name WS name )
            dbg.enterAlt(1);

            // Onto.g:44:16: name WS name WS name
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(44,16);
            pushFollow(FOLLOW_name_in_triple_entry303);
            name19=name();

            state._fsp--;

            adaptor.addChild(root_0, name19.getTree());
            dbg.location(44,21);
            WS20=(Token)match(input,WS,FOLLOW_WS_in_triple_entry305); 
            WS20_tree = (Object)adaptor.create(WS20);
            adaptor.addChild(root_0, WS20_tree);

            dbg.location(44,24);
            pushFollow(FOLLOW_name_in_triple_entry307);
            name21=name();

            state._fsp--;

            adaptor.addChild(root_0, name21.getTree());
            dbg.location(44,29);
            WS22=(Token)match(input,WS,FOLLOW_WS_in_triple_entry309); 
            WS22_tree = (Object)adaptor.create(WS22);
            adaptor.addChild(root_0, WS22_tree);

            dbg.location(44,32);
            pushFollow(FOLLOW_name_in_triple_entry311);
            name23=name();

            state._fsp--;

            adaptor.addChild(root_0, name23.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(44, 37);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "triple_entry");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "triple_entry"

    public static class singleline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "singleline"
    // Onto.g:46:1: singleline : ( importline | prefixline );
    public final OntoParser.singleline_return singleline() throws RecognitionException {
        OntoParser.singleline_return retval = new OntoParser.singleline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        OntoParser.importline_return importline24 = null;

        OntoParser.prefixline_return prefixline25 = null;



        try { dbg.enterRule(getGrammarFileName(), "singleline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(46, 1);

        try {
            // Onto.g:47:5: ( importline | prefixline )
            int alt8=2;
            try { dbg.enterDecision(8, decisionCanBacktrack[8]);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==IMPORT) ) {
                alt8=1;
            }
            else if ( (LA8_0==PRFX) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:47:7: importline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(47,7);
                    pushFollow(FOLLOW_importline_in_singleline326);
                    importline24=importline();

                    state._fsp--;

                    adaptor.addChild(root_0, importline24.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // Onto.g:48:7: prefixline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(48,7);
                    pushFollow(FOLLOW_prefixline_in_singleline335);
                    prefixline25=prefixline();

                    state._fsp--;

                    adaptor.addChild(root_0, prefixline25.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(49, 5);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "singleline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "singleline"

    public static class headline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "headline"
    // Onto.g:51:1: headline : ( defineline | assertline | tableline );
    public final OntoParser.headline_return headline() throws RecognitionException {
        OntoParser.headline_return retval = new OntoParser.headline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        OntoParser.defineline_return defineline26 = null;

        OntoParser.assertline_return assertline27 = null;

        OntoParser.tableline_return tableline28 = null;



        try { dbg.enterRule(getGrammarFileName(), "headline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(51, 1);

        try {
            // Onto.g:52:5: ( defineline | assertline | tableline )
            int alt9=3;
            try { dbg.enterDecision(9, decisionCanBacktrack[9]);

            switch ( input.LA(1) ) {
            case DEFINE:
                {
                alt9=1;
                }
                break;
            case ASSERT:
                {
                alt9=2;
                }
                break;
            case TABLE:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:52:7: defineline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(52,7);
                    pushFollow(FOLLOW_defineline_in_headline353);
                    defineline26=defineline();

                    state._fsp--;

                    adaptor.addChild(root_0, defineline26.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // Onto.g:53:7: assertline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(53,7);
                    pushFollow(FOLLOW_assertline_in_headline362);
                    assertline27=assertline();

                    state._fsp--;

                    adaptor.addChild(root_0, assertline27.getTree());

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // Onto.g:54:7: tableline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(54,7);
                    pushFollow(FOLLOW_tableline_in_headline371);
                    tableline28=tableline();

                    state._fsp--;

                    adaptor.addChild(root_0, tableline28.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(55, 5);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "headline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "headline"

    public static class innerline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "innerline"
    // Onto.g:57:1: innerline : ( defineline | assertline | classline | restrictline );
    public final OntoParser.innerline_return innerline() throws RecognitionException {
        OntoParser.innerline_return retval = new OntoParser.innerline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        OntoParser.defineline_return defineline29 = null;

        OntoParser.assertline_return assertline30 = null;

        OntoParser.classline_return classline31 = null;

        OntoParser.restrictline_return restrictline32 = null;



        try { dbg.enterRule(getGrammarFileName(), "innerline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(57, 1);

        try {
            // Onto.g:58:5: ( defineline | assertline | classline | restrictline )
            int alt10=4;
            try { dbg.enterDecision(10, decisionCanBacktrack[10]);

            switch ( input.LA(1) ) {
            case DEFINE:
                {
                alt10=1;
                }
                break;
            case ASSERT:
                {
                alt10=2;
                }
                break;
            case LITERAL:
            case VAR:
            case QUOTED:
            case PREFIXED:
                {
                int LA10_3 = input.LA(2);

                if ( (LA10_3==WS) ) {
                    int LA10_4 = input.LA(3);

                    if ( ((LA10_4>=SOME && LA10_4<=VALUE)) ) {
                        alt10=4;
                    }
                    else if ( (LA10_4==NEWLINE) ) {
                        alt10=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 4, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                }
                else if ( (LA10_3==NEWLINE) ) {
                    alt10=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 3, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:58:7: defineline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(58,7);
                    pushFollow(FOLLOW_defineline_in_innerline390);
                    defineline29=defineline();

                    state._fsp--;

                    adaptor.addChild(root_0, defineline29.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // Onto.g:59:7: assertline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(59,7);
                    pushFollow(FOLLOW_assertline_in_innerline398);
                    assertline30=assertline();

                    state._fsp--;

                    adaptor.addChild(root_0, assertline30.getTree());

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // Onto.g:60:7: classline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(60,7);
                    pushFollow(FOLLOW_classline_in_innerline406);
                    classline31=classline();

                    state._fsp--;

                    adaptor.addChild(root_0, classline31.getTree());

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // Onto.g:61:7: restrictline
                    {
                    root_0 = (Object)adaptor.nil();

                    dbg.location(61,7);
                    pushFollow(FOLLOW_restrictline_in_innerline414);
                    restrictline32=restrictline();

                    state._fsp--;

                    adaptor.addChild(root_0, restrictline32.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(62, 5);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "innerline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "innerline"

    public static class defineline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "defineline"
    // Onto.g:64:1: defineline : DEFINE WS name ;
    public final OntoParser.defineline_return defineline() throws RecognitionException {
        OntoParser.defineline_return retval = new OntoParser.defineline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DEFINE33=null;
        Token WS34=null;
        OntoParser.name_return name35 = null;


        Object DEFINE33_tree=null;
        Object WS34_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "defineline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(64, 1);

        try {
            // Onto.g:64:12: ( DEFINE WS name )
            dbg.enterAlt(1);

            // Onto.g:64:14: DEFINE WS name
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(64,14);
            DEFINE33=(Token)match(input,DEFINE,FOLLOW_DEFINE_in_defineline427); 
            DEFINE33_tree = (Object)adaptor.create(DEFINE33);
            adaptor.addChild(root_0, DEFINE33_tree);

            dbg.location(64,21);
            WS34=(Token)match(input,WS,FOLLOW_WS_in_defineline429); 
            WS34_tree = (Object)adaptor.create(WS34);
            adaptor.addChild(root_0, WS34_tree);

            dbg.location(64,24);
            pushFollow(FOLLOW_name_in_defineline431);
            name35=name();

            state._fsp--;

            adaptor.addChild(root_0, name35.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(64, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "defineline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "defineline"

    public static class assertline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assertline"
    // Onto.g:65:1: assertline : ASSERT WS name ;
    public final OntoParser.assertline_return assertline() throws RecognitionException {
        OntoParser.assertline_return retval = new OntoParser.assertline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASSERT36=null;
        Token WS37=null;
        OntoParser.name_return name38 = null;


        Object ASSERT36_tree=null;
        Object WS37_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "assertline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // Onto.g:65:12: ( ASSERT WS name )
            dbg.enterAlt(1);

            // Onto.g:65:14: ASSERT WS name
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(65,14);
            ASSERT36=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_assertline440); 
            ASSERT36_tree = (Object)adaptor.create(ASSERT36);
            adaptor.addChild(root_0, ASSERT36_tree);

            dbg.location(65,21);
            WS37=(Token)match(input,WS,FOLLOW_WS_in_assertline442); 
            WS37_tree = (Object)adaptor.create(WS37);
            adaptor.addChild(root_0, WS37_tree);

            dbg.location(65,24);
            pushFollow(FOLLOW_name_in_assertline444);
            name38=name();

            state._fsp--;

            adaptor.addChild(root_0, name38.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(65, 29);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "assertline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "assertline"

    public static class tableline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tableline"
    // Onto.g:66:1: tableline : TABLE WS LITERAL ( WS ( VAR | QUOTED ) )* ;
    public final OntoParser.tableline_return tableline() throws RecognitionException {
        OntoParser.tableline_return retval = new OntoParser.tableline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TABLE39=null;
        Token WS40=null;
        Token LITERAL41=null;
        Token WS42=null;
        Token set43=null;

        Object TABLE39_tree=null;
        Object WS40_tree=null;
        Object LITERAL41_tree=null;
        Object WS42_tree=null;
        Object set43_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "tableline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(66, 1);

        try {
            // Onto.g:66:11: ( TABLE WS LITERAL ( WS ( VAR | QUOTED ) )* )
            dbg.enterAlt(1);

            // Onto.g:66:13: TABLE WS LITERAL ( WS ( VAR | QUOTED ) )*
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(66,13);
            TABLE39=(Token)match(input,TABLE,FOLLOW_TABLE_in_tableline453); 
            TABLE39_tree = (Object)adaptor.create(TABLE39);
            adaptor.addChild(root_0, TABLE39_tree);

            dbg.location(66,19);
            WS40=(Token)match(input,WS,FOLLOW_WS_in_tableline455); 
            WS40_tree = (Object)adaptor.create(WS40);
            adaptor.addChild(root_0, WS40_tree);

            dbg.location(66,22);
            LITERAL41=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_tableline457); 
            LITERAL41_tree = (Object)adaptor.create(LITERAL41);
            adaptor.addChild(root_0, LITERAL41_tree);

            dbg.location(66,30);
            // Onto.g:66:30: ( WS ( VAR | QUOTED ) )*
            try { dbg.enterSubRule(11);

            loop11:
            do {
                int alt11=2;
                try { dbg.enterDecision(11, decisionCanBacktrack[11]);

                int LA11_0 = input.LA(1);

                if ( (LA11_0==WS) ) {
                    int LA11_1 = input.LA(2);

                    if ( ((LA11_1>=VAR && LA11_1<=QUOTED)) ) {
                        alt11=1;
                    }


                }


                } finally {dbg.exitDecision(11);}

                switch (alt11) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // Onto.g:66:31: WS ( VAR | QUOTED )
            	    {
            	    dbg.location(66,31);
            	    WS42=(Token)match(input,WS,FOLLOW_WS_in_tableline460); 
            	    WS42_tree = (Object)adaptor.create(WS42);
            	    adaptor.addChild(root_0, WS42_tree);

            	    dbg.location(66,34);
            	    set43=(Token)input.LT(1);
            	    if ( (input.LA(1)>=VAR && input.LA(1)<=QUOTED) ) {
            	        input.consume();
            	        adaptor.addChild(root_0, (Object)adaptor.create(set43));
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);
            } finally {dbg.exitSubRule(11);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(66, 51);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "tableline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "tableline"

    public static class importline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "importline"
    // Onto.g:67:1: importline : IMPORT WS LITERAL WS QUOTED ;
    public final OntoParser.importline_return importline() throws RecognitionException {
        OntoParser.importline_return retval = new OntoParser.importline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT44=null;
        Token WS45=null;
        Token LITERAL46=null;
        Token WS47=null;
        Token QUOTED48=null;

        Object IMPORT44_tree=null;
        Object WS45_tree=null;
        Object LITERAL46_tree=null;
        Object WS47_tree=null;
        Object QUOTED48_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "importline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(67, 1);

        try {
            // Onto.g:67:12: ( IMPORT WS LITERAL WS QUOTED )
            dbg.enterAlt(1);

            // Onto.g:67:14: IMPORT WS LITERAL WS QUOTED
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(67,14);
            IMPORT44=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_importline479); 
            IMPORT44_tree = (Object)adaptor.create(IMPORT44);
            adaptor.addChild(root_0, IMPORT44_tree);

            dbg.location(67,21);
            WS45=(Token)match(input,WS,FOLLOW_WS_in_importline481); 
            WS45_tree = (Object)adaptor.create(WS45);
            adaptor.addChild(root_0, WS45_tree);

            dbg.location(67,24);
            LITERAL46=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_importline483); 
            LITERAL46_tree = (Object)adaptor.create(LITERAL46);
            adaptor.addChild(root_0, LITERAL46_tree);

            dbg.location(67,32);
            WS47=(Token)match(input,WS,FOLLOW_WS_in_importline485); 
            WS47_tree = (Object)adaptor.create(WS47);
            adaptor.addChild(root_0, WS47_tree);

            dbg.location(67,35);
            QUOTED48=(Token)match(input,QUOTED,FOLLOW_QUOTED_in_importline487); 
            QUOTED48_tree = (Object)adaptor.create(QUOTED48);
            adaptor.addChild(root_0, QUOTED48_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(67, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "importline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "importline"

    public static class prefixline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "prefixline"
    // Onto.g:68:1: prefixline : PRFX WS LITERAL WS QUOTED ;
    public final OntoParser.prefixline_return prefixline() throws RecognitionException {
        OntoParser.prefixline_return retval = new OntoParser.prefixline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRFX49=null;
        Token WS50=null;
        Token LITERAL51=null;
        Token WS52=null;
        Token QUOTED53=null;

        Object PRFX49_tree=null;
        Object WS50_tree=null;
        Object LITERAL51_tree=null;
        Object WS52_tree=null;
        Object QUOTED53_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "prefixline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(68, 1);

        try {
            // Onto.g:68:12: ( PRFX WS LITERAL WS QUOTED )
            dbg.enterAlt(1);

            // Onto.g:68:14: PRFX WS LITERAL WS QUOTED
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(68,14);
            PRFX49=(Token)match(input,PRFX,FOLLOW_PRFX_in_prefixline496); 
            PRFX49_tree = (Object)adaptor.create(PRFX49);
            adaptor.addChild(root_0, PRFX49_tree);

            dbg.location(68,19);
            WS50=(Token)match(input,WS,FOLLOW_WS_in_prefixline498); 
            WS50_tree = (Object)adaptor.create(WS50);
            adaptor.addChild(root_0, WS50_tree);

            dbg.location(68,22);
            LITERAL51=(Token)match(input,LITERAL,FOLLOW_LITERAL_in_prefixline500); 
            LITERAL51_tree = (Object)adaptor.create(LITERAL51);
            adaptor.addChild(root_0, LITERAL51_tree);

            dbg.location(68,30);
            WS52=(Token)match(input,WS,FOLLOW_WS_in_prefixline502); 
            WS52_tree = (Object)adaptor.create(WS52);
            adaptor.addChild(root_0, WS52_tree);

            dbg.location(68,33);
            QUOTED53=(Token)match(input,QUOTED,FOLLOW_QUOTED_in_prefixline504); 
            QUOTED53_tree = (Object)adaptor.create(QUOTED53);
            adaptor.addChild(root_0, QUOTED53_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(68, 40);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "prefixline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "prefixline"

    public static class classline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classline"
    // Onto.g:70:1: classline : name ;
    public final OntoParser.classline_return classline() throws RecognitionException {
        OntoParser.classline_return retval = new OntoParser.classline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        OntoParser.name_return name54 = null;



        try { dbg.enterRule(getGrammarFileName(), "classline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(70, 1);

        try {
            // Onto.g:70:11: ( name )
            dbg.enterAlt(1);

            // Onto.g:70:13: name
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(70,13);
            pushFollow(FOLLOW_name_in_classline514);
            name54=name();

            state._fsp--;

            adaptor.addChild(root_0, name54.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(70, 18);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "classline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "classline"

    public static class restrictline_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "restrictline"
    // Onto.g:71:1: restrictline : name WS ( SOME | ONLY | VALUE ) ( WS name )? ;
    public final OntoParser.restrictline_return restrictline() throws RecognitionException {
        OntoParser.restrictline_return retval = new OntoParser.restrictline_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS56=null;
        Token set57=null;
        Token WS58=null;
        OntoParser.name_return name55 = null;

        OntoParser.name_return name59 = null;


        Object WS56_tree=null;
        Object set57_tree=null;
        Object WS58_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "restrictline");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(71, 1);

        try {
            // Onto.g:71:14: ( name WS ( SOME | ONLY | VALUE ) ( WS name )? )
            dbg.enterAlt(1);

            // Onto.g:71:16: name WS ( SOME | ONLY | VALUE ) ( WS name )?
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(71,16);
            pushFollow(FOLLOW_name_in_restrictline523);
            name55=name();

            state._fsp--;

            adaptor.addChild(root_0, name55.getTree());
            dbg.location(71,21);
            WS56=(Token)match(input,WS,FOLLOW_WS_in_restrictline525); 
            WS56_tree = (Object)adaptor.create(WS56);
            adaptor.addChild(root_0, WS56_tree);

            dbg.location(71,24);
            set57=(Token)input.LT(1);
            if ( (input.LA(1)>=SOME && input.LA(1)<=VALUE) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set57));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(71,46);
            // Onto.g:71:46: ( WS name )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12, decisionCanBacktrack[12]);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==WS) ) {
                int LA12_1 = input.LA(2);

                if ( ((LA12_1>=LITERAL && LA12_1<=PREFIXED)) ) {
                    alt12=1;
                }
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // Onto.g:71:47: WS name
                    {
                    dbg.location(71,47);
                    WS58=(Token)match(input,WS,FOLLOW_WS_in_restrictline540); 
                    WS58_tree = (Object)adaptor.create(WS58);
                    adaptor.addChild(root_0, WS58_tree);

                    dbg.location(71,50);
                    pushFollow(FOLLOW_name_in_restrictline542);
                    name59=name();

                    state._fsp--;

                    adaptor.addChild(root_0, name59.getTree());

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(71, 57);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "restrictline");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "restrictline"

    public static class name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "name"
    // Onto.g:73:1: name : ( PREFIXED | LITERAL | QUOTED | VAR );
    public final OntoParser.name_return name() throws RecognitionException {
        OntoParser.name_return retval = new OntoParser.name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set60=null;

        Object set60_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "name");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(73, 1);

        try {
            // Onto.g:73:6: ( PREFIXED | LITERAL | QUOTED | VAR )
            dbg.enterAlt(1);

            // Onto.g:
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(73,6);
            set60=(Token)input.LT(1);
            if ( (input.LA(1)>=LITERAL && input.LA(1)<=PREFIXED) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set60));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(73, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "name");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "name"

    public static class eol_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eol"
    // Onto.g:75:1: eol : ( ( WS )? NEWLINE )+ ;
    public final OntoParser.eol_return eol() throws RecognitionException {
        OntoParser.eol_return retval = new OntoParser.eol_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WS61=null;
        Token NEWLINE62=null;

        Object WS61_tree=null;
        Object NEWLINE62_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "eol");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(75, 1);

        try {
            // Onto.g:75:5: ( ( ( WS )? NEWLINE )+ )
            dbg.enterAlt(1);

            // Onto.g:75:7: ( ( WS )? NEWLINE )+
            {
            root_0 = (Object)adaptor.nil();

            dbg.location(75,7);
            // Onto.g:75:7: ( ( WS )? NEWLINE )+
            int cnt14=0;
            try { dbg.enterSubRule(14);

            loop14:
            do {
                int alt14=2;
                try { dbg.enterDecision(14, decisionCanBacktrack[14]);

                int LA14_0 = input.LA(1);

                if ( (LA14_0==WS||LA14_0==NEWLINE) ) {
                    alt14=1;
                }


                } finally {dbg.exitDecision(14);}

                switch (alt14) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // Onto.g:75:8: ( WS )? NEWLINE
            	    {
            	    dbg.location(75,8);
            	    // Onto.g:75:8: ( WS )?
            	    int alt13=2;
            	    try { dbg.enterSubRule(13);
            	    try { dbg.enterDecision(13, decisionCanBacktrack[13]);

            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==WS) ) {
            	        alt13=1;
            	    }
            	    } finally {dbg.exitDecision(13);}

            	    switch (alt13) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // Onto.g:75:8: WS
            	            {
            	            dbg.location(75,8);
            	            WS61=(Token)match(input,WS,FOLLOW_WS_in_eol577); 
            	            WS61_tree = (Object)adaptor.create(WS61);
            	            adaptor.addChild(root_0, WS61_tree);


            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(13);}

            	    dbg.location(75,12);
            	    NEWLINE62=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eol580); 
            	    NEWLINE62_tree = (Object)adaptor.create(NEWLINE62);
            	    adaptor.addChild(root_0, NEWLINE62_tree);


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt14++;
            } while (true);
            } finally {dbg.exitSubRule(14);}


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        dbg.location(75, 22);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "eol");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "eol"

    // Delegated rules


 

    public static final BitSet FOLLOW_eol_in_template214 = new BitSet(new long[]{0x0000000000006830L});
    public static final BitSet FOLLOW_statement_in_template217 = new BitSet(new long[]{0x0000000000006830L});
    public static final BitSet FOLLOW_EOF_in_template220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_headline_in_statement235 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_eol_in_statement237 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_triples_in_statement239 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_TAB_in_statement243 = new BitSet(new long[]{0x0000000001E10030L});
    public static final BitSet FOLLOW_innerline_in_statement246 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_eol_in_statement248 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_singleline_in_statement259 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_eol_in_statement261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TAB_in_triples277 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_TRIPLES_in_triples279 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_eol_in_triples281 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_TAB_in_triples284 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_TAB_in_triples286 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_triple_entry_in_triples288 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_eol_in_triples290 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_name_in_triple_entry303 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_triple_entry305 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_name_in_triple_entry307 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_triple_entry309 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_name_in_triple_entry311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importline_in_singleline326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefixline_in_singleline335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defineline_in_headline353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assertline_in_headline362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableline_in_headline371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defineline_in_innerline390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assertline_in_innerline398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classline_in_innerline406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_restrictline_in_innerline414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DEFINE_in_defineline427 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_defineline429 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_name_in_defineline431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_assertline440 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_assertline442 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_name_in_assertline444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TABLE_in_tableline453 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_tableline455 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_LITERAL_in_tableline457 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_WS_in_tableline460 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_set_in_tableline462 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_IMPORT_in_importline479 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_importline481 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_LITERAL_in_importline483 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_importline485 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_QUOTED_in_importline487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRFX_in_prefixline496 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_prefixline498 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_LITERAL_in_prefixline500 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_prefixline502 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_QUOTED_in_prefixline504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_classline514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_restrictline523 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_WS_in_restrictline525 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_set_in_restrictline527 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_WS_in_restrictline540 = new BitSet(new long[]{0x0000000001E00000L});
    public static final BitSet FOLLOW_name_in_restrictline542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_name0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_eol577 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NEWLINE_in_eol580 = new BitSet(new long[]{0x0000000002100002L});

}