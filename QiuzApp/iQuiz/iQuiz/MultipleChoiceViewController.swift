//
//  MultipleChoiceViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 06/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class MultipleChoiceViewController: UIViewController {
    
    @IBOutlet weak var QNumberLabel: UILabel!
    @IBOutlet weak var QuestionLabel: UILabel!
    @IBOutlet var answerButtons: [UIButton]!
    var selectedAnswers = ["0","0","0","0"]
    
    var quizQuestions=[]
    var currentQustionCounter=Int()
    var totalQuestions=Int()
    // var correctAnswerCount=Int()
    var wrongAnswerCount=0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("MultipleChoiceViewController")
        let query=PFQuery(className: "QuizToday")
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            print("Ho")
            if error == nil {
                 print("MultipleChoiceViewController : error == nil")
                if let returnedobjects = objects{
                    print("MultipleChoiceViewController : returnedobjects")
                    self.quizQuestions=returnedobjects
                    //self.correctAnswerCount=0
                    self.currentQustionCounter=0
                    self.totalQuestions=returnedobjects.count
                    let object=returnedobjects[0]
                    let question=object["question"] as! String
                    self.QuestionLabel.text=question
                    var QueNumber = self.currentQustionCounter + 1
                    self.QNumberLabel.text="Que.\(QueNumber)"
                    let answers=object["Answers"] as! String
                    let answersArr = answers.componentsSeparatedByString(";")
                    for i in 0..<answersArr.count{
                        self.answerButtons[i].setTitle(answersArr[i], forState: UIControlState.Normal)                        }
                }
                
            }else{
                print("error")
            }
        }
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func changeSelection(sender: UIButton) {
        if(sender.titleColorForState(UIControlState.Normal) == UIColor.greenColor())
        {
            sender.setTitleColor(UIColor.blueColor(), forState: UIControlState.Normal)
        }else{
            sender.setTitleColor(UIColor.greenColor(), forState: UIControlState.Normal)
        }
    }
    
    
    
    func fetchNextQuestion(index:Int){
        print("ha")
        if(index < totalQuestions){
            let object=self.quizQuestions[index] 
            let question=object["question"] as! String
            self.QuestionLabel.text=question
            self.QNumberLabel.text="Que.\(self.currentQustionCounter)"
            let answers=object["Answers"] as! String
            let answersArr = answers.componentsSeparatedByString(";")
            for i in 0..<answersArr.count{
                self.answerButtons[i].setTitle(answersArr[i], forState:UIControlState.Normal)
            }
        }else{
            
            self.QuestionLabel.text=" *    Quiz Finished    * "
            self.QNumberLabel.text=""
            for i in 0..<answerButtons.count{
                self.answerButtons[i].setTitle("", forState:UIControlState.Normal)
            }
            
            sendResultBack()
            
            let alert = UIAlertController(title: "Quiz finish",
                message: "Your Quiz is finished",
                preferredStyle: .Alert)
            let action = UIAlertAction(title: "OK", style: .Default,
                handler: { (action: UIAlertAction!) in
                    print("Handle Cancel Logic here")
                    print("ha")
                    let mainViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Main") as? MainToolBarViewController
                    self.navigationController?.pushViewController(mainViewControllerObj!, animated: true)
                    
            })
            alert.addAction(action)
            presentViewController(alert, animated: true, completion: nil)
        }
        
    }
    
    
    func sendResultBack()
    {
        self.QuestionLabel.text=""
        for i in 0..<answerButtons.count{
            self.answerButtons[i].setTitle("", forState:UIControlState.Normal)
        }
        
        let score=PFObject(className: "ScoreReport")
        let currentUserName = PFUser.currentUser()?.username!
        score["username"]=currentUserName
        score["quizName"]="MyQuize"
        
        
        let finalScore = totalQuestions * 3 - wrongAnswerCount
        score["quizScore"] = finalScore
        
        print("score = \(finalScore)")
        
        score.saveInBackgroundWithBlock { (sucessful, error) -> Void in
            if sucessful {
                print("Sucess")
            }else{
                print("Sorry")
            }
            
        }
        
    }
    
    
    @IBAction func gotoNextQuestion(sender: UIButton) {
        print("5")
        let selectedAnswer = sender.titleForState(UIControlState.Normal)
        let object = self.quizQuestions[self.currentQustionCounter]
        let correctAnswer=object["correctAnswer"] as! String
        
        let correctanswersArr = correctAnswer.componentsSeparatedByString(";")
        print("\(selectedAnswer) ==> \(correctAnswer)")
        
        let TotalCorrectAnswer = correctanswersArr.count
        var wrongAnswer = 0
        for i in 0..<TotalCorrectAnswer{
            for var j in 0..<answerButtons.count{
                if self.answerButtons[j].titleColorForState(UIControlState.Normal) == UIColor.greenColor(){
                    if(self.answerButtons[j].titleLabel?.text! == correctanswersArr[i])
                    {
                       
                    }else{
                         wrongAnswer = wrongAnswer+1
                    }
                    self.answerButtons[j].setTitleColor(UIColor.blueColor(), forState: UIControlState.Normal)
                }else{
                    
                }
            }
        }
        
        if(TotalCorrectAnswer<wrongAnswer)
        {
            wrongAnswerCount = wrongAnswerCount+TotalCorrectAnswer
        }
        
        self.currentQustionCounter+=1
        fetchNextQuestion(self.currentQustionCounter)
    }
    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }
    */
    
}
