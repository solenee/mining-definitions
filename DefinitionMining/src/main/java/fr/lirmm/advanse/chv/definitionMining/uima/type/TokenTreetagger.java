

/* First created by JCasGen Thu Apr 14 19:09:23 CEST 2016 */
package fr.lirmm.advanse.chv.definitionMining.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jun 15 11:44:47 CEST 2016
 * XML source: /home/monordi/releases/mining-definitions/DefinitionMining/src/main/resources/desc/type/DefinitionMining.xml
 * @generated */
public class TokenTreetagger extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TokenTreetagger.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenTreetagger() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TokenTreetagger(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TokenTreetagger(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TokenTreetagger(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLemma() {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLemma(String v) {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_lemma, v);}    
   
    
  //*--------------*
  //* Feature: pos

  /** getter for pos - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPos() {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPos(String v) {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: word

  /** getter for word - gets 
   * @generated
   * @return value of the feature 
   */
  public String getWord() {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_word);}
    
  /** setter for word - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setWord(String v) {
    if (TokenTreetagger_Type.featOkTst && ((TokenTreetagger_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "fr.lirmm.advanse.chv.definitionMining.uima.type.TokenTreetagger");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenTreetagger_Type)jcasType).casFeatCode_word, v);}    
  }

    