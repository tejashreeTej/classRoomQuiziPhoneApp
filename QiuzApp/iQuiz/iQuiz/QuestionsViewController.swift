//
//  QuestionsViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 03/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class QuestionsViewController: UIViewController {
    
    var quizeCategory:String = ""
    @IBOutlet weak var QuestionLabel: UILabel!
    @IBOutlet weak var QNumberLabel: UILabel!
    @IBOutlet var Buttons: [UIButton]!
    //var quizQuestions:[Quiz] = []
    var quizQuestions=[]
    var QustionCounter=Int()
    var totalQuestions=Int()
    var correctAnswerCount=Int()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.hidesBackButton = true
        // Do any additional setup after loading the view, typically from a nib.
        print("3")
        print("quizeCategory => \(quizeCategory)")
        
        //var DestViewController : TrailQuizViewController = segue.destinationViewController as! TrailQuizViewController
        
        //print("DestViewController.selectedCategory => \(DestViewController.selectedCategory)")
        
        
        let query=PFQuery(className: "Questions")
        query.whereKey("category", equalTo: quizeCategory)
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil {
                
                if let returnedobjects = objects{
                    self.quizQuestions=returnedobjects
                    self.correctAnswerCount=0
                    self.QustionCounter=0
                    self.totalQuestions=returnedobjects.count
                    let object=returnedobjects[0]
                    let question=object["question"] as! String
                    self.QuestionLabel.numberOfLines = 3
                    self.QuestionLabel.text=question
                    let currentQCount = self.QustionCounter + 1
                    self.QNumberLabel.text="Que.\(currentQCount)"
                    let answers=object["Answers"] as! String
                    let answersArr = answers.componentsSeparatedByString(";")
                    for i in 0..<answersArr.count{
                        self.Buttons[i].setTitle(answersArr[i], forState: UIControlState.Normal)                        }
                }
                
            }else{
                print("error")
            }
        }
        print("4")
    }
    //--------
    func fetchNextQuestion(index:Int){
        print("ha")
        if(index < totalQuestions){
            let object=self.quizQuestions[index] as! [NSObject:AnyObject]
            let question=object["question"] as! String
             self.QuestionLabel.numberOfLines = 3
            self.QuestionLabel.text=question
            let currentQCount = self.QustionCounter + 1
            self.QNumberLabel.text="Que.\(currentQCount)"
            //self.QNumberLabel.text="Que.\(self.QustionCounter)"
            let answers=object["Answers"] as! String
            let answersArr = answers.componentsSeparatedByString(";")
            for i in 0..<answersArr.count{
                
                self.Buttons[i].setTitle(answersArr[i], forState:UIControlState.Normal)
            }
        }else{
            
            self.QuestionLabel.text=" *    Quiz Finished    * "
            self.QNumberLabel.text=""
            for i in 0..<Buttons.count{
                self.Buttons[i].setTitle("", forState:UIControlState.Normal)
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
        for i in 0..<Buttons.count{
            self.Buttons[i].setTitle("", forState:UIControlState.Normal)
        }
        
        let score=PFObject(className: "ScoreReport")
        let currentUserName = PFUser.currentUser()?.username!
        score["username"]=currentUserName
        score["quizName"]="IOS Quize"
        score["quizScore"]=self.correctAnswerCount
        print("score = \(self.correctAnswerCount)")
        score.saveInBackgroundWithBlock { (sucessful, error) -> Void in
            if sucessful {
                print("Sucess")
            }else{
                print("Sorry")
            }
            
        }
        
    }
    
    @IBAction func answerSelected(sender: UIButton) {
        print("5")
        let selectedAnswer = sender.titleForState(UIControlState.Normal)
        let object = self.quizQuestions[self.QustionCounter] as! [NSObject:AnyObject]
        let correctAnswer=object["correctAnswer"] as! String
        print("\(selectedAnswer) ==> \(correctAnswer)")
        
        if(selectedAnswer == correctAnswer){
            self.correctAnswerCount+=1
        }
        self.QustionCounter+=1
        fetchNextQuestion(self.QustionCounter)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}

