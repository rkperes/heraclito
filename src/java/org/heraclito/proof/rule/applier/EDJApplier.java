/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.heraclito.proof.rule.applier;

import org.heraclito.proof.Expression;
import org.heraclito.proof.Operator;
import org.heraclito.proof.ProofException;
import org.heraclito.proof.rule.Applier;
import org.heraclito.proof.rule.Rule;

/**
 *
 * @author gia
 */
public class EDJApplier extends Applier {

    public EDJApplier(Rule.ID rule) {
        super(rule);
    }

    @Override
    public Expression apply() throws ProofException {
        checkParameters();
        Expression firstInnerExpression = getInnerExpression(0);
        Expression secondInnerExpression = getInnerExpression(1);
        Expression thirdInnerExpression = getInnerExpression(2);

        Expression disjunctionExpression, firstImplication, secondImplication;

        if (Operator.DISJUNCTION.equals(firstInnerExpression.getMainOperator())) {            
            if (!Operator.IMPLICATION.equals(secondInnerExpression.getMainOperator())
                    || !Operator.IMPLICATION.equals(thirdInnerExpression.getMainOperator()))  {
                throw new ProofException("exception_invalid_main_operator");
            }
            
            disjunctionExpression = firstInnerExpression;
            firstImplication = secondInnerExpression;
            secondImplication = thirdInnerExpression;
        } else if (Operator.DISJUNCTION.equals(secondInnerExpression.getMainOperator())) {
            if (!Operator.IMPLICATION.equals(firstInnerExpression.getMainOperator())
                    || !Operator.IMPLICATION.equals(thirdInnerExpression.getMainOperator()))  {
                throw new ProofException("exception_invalid_main_operator");
            }
            
            disjunctionExpression = secondInnerExpression;
            firstImplication = firstInnerExpression;
            secondImplication = thirdInnerExpression;
        } else if (Operator.DISJUNCTION.equals(thirdInnerExpression.getMainOperator())) {
            if (!Operator.IMPLICATION.equals(firstInnerExpression.getMainOperator())
                    || !Operator.IMPLICATION.equals(secondInnerExpression.getMainOperator()))  {
                throw new ProofException("exception_invalid_main_operator");
            }
            
            disjunctionExpression = thirdInnerExpression;
            firstImplication = firstInnerExpression;
            secondImplication = secondInnerExpression;
        } else {
            throw new ProofException("exception_invalid_main_operator");
        }
                       
        if(!firstImplication.getRightExpression()
                .equals(secondImplication.getRightExpression())) {
            throw new ProofException("exception_invalid_expression");
        }        
        
        if(disjunctionExpression.getLeftExpression()
                .equals(firstImplication.getLeftExpression())) {
            if(disjunctionExpression.getRightExpression()
                    .equals(secondImplication.getRightExpression())) {
                
            } else {
                throw new ProofException("exception_invalid_expression");
            }
            
            
        }
        
        return new Expression("");
    }

}
