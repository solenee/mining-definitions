

/* First created by JCasGen Fri Apr 15 11:50:23 CEST 2016 */
package fr.lirmm.advanse.chv.definitionMining.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jun 15 11:44:47 CEST 2016
 * XML source: /home/monordi/releases/mining-definitions/DefinitionMining/src/main/resources/desc/type/DefinitionMining.xml
 * @generated */
public class ChunkTreetagger extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ChunkTreetagger.class);
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
  protected ChunkTreetagger() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ChunkTreetagger(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ChunkTreetagger(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ChunkTreetagger(JCas jcas, int begin, int end) {
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
  //* Feature: tagType

  /** getter for tagType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTagType() {
    if (ChunkTreetagger_Type.featOkTst && ((ChunkTreetagger_Type)jcasType).casFeat_tagType == null)
      jcasType.jcas.throwFeatMissing("tagType", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChunkTreetagger_Type)jcasType).casFeatCode_tagType);}
    
  /** setter for tagType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTagType(String v) {
    if (ChunkTreetagger_Type.featOkTst && ((ChunkTreetagger_Type)jcasType).casFeat_tagType == null)
      jcasType.jcas.throwFeatMissing("tagType", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChunkTreetagger_Type)jcasType).casFeatCode_tagType, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (ChunkTreetagger_Type.featOkTst && ((ChunkTreetagger_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ChunkTreetagger_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (ChunkTreetagger_Type.featOkTst && ((ChunkTreetagger_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "fr.lirmm.advanse.chv.definitionMining.uima.type.ChunkTreetagger");
    jcasType.ll_cas.ll_setStringValue(addr, ((ChunkTreetagger_Type)jcasType).casFeatCode_text, v);}    
  }

    