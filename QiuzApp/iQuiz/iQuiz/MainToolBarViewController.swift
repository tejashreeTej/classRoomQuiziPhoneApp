//
//  MainToolBarViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 03/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse
class MainToolBarViewController: UIViewController {
    
    private var logInViewController: LogInViewController!
    private var quizesViewController:TrailQuizViewController!
    private var scoreReportViewController:ScoreReportViewController!
    private var todayQuizViewController:TodayQuizViewController!
    
    var mainQuizCategories = [String]()
    var mainQuizScores = [String]()

    func loadMainTableView() /*-> Bool*/ {
        let query=PFQuery(className: "Questions")
        query.selectKeys(["category"])
        
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil {
                if let returnedobjects = objects{
                    for object in returnedobjects{
                        print(object["category"] as! String)
                        if self.mainQuizCategories.contains(object["category"] as! String){
                            print("s")
                        }else {
                            self.mainQuizCategories.append(object["category"] as! String)
                        }
                    }
                    
                }else{
                    print("error")
                }
            }
        }
    }
    
   
    func loadMainScoreTableView()
    {

        var myReport = String()
        
        let query=PFQuery(className: "ScoreReport")
        let currentUser=PFUser.currentUser()
        
        if currentUser != nil{
            let currentUserName = currentUser?.username;
            query.whereKey("username", equalTo: currentUserName!)
            
            query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
                if error == nil {
                    print("error == nil")
                    if let returnedobjects = objects{
                        for var object in returnedobjects{
                            let quizeName = object["quizName"] as! String
                            let quizeScore = object["quizScore"]!
                            
                            myReport = "Quize:\(quizeName)   Score :\(quizeScore)"
                            self.mainQuizScores.append(myReport)
                        }
                        
                    }else{
                        print("loadMainScoreTableView() -> No values in objects")
                    }
                }else{
                    print("loadMainScoreTableView() -> Error occured")
                }
                
            }
        }
       // print("*******END loadMainScoreTableView()()******")
    }

    private func switchViewController(from fromVC:UIViewController?,
        to toVC:UIViewController?) {
            if fromVC != nil {
                print("fromVC != nil")
                fromVC!.willMoveToParentViewController(nil)
                fromVC!.view.removeFromSuperview()
                fromVC!.removeFromParentViewController()
            }
            
            if toVC != nil {
                print("toVC != nil")
                self.addChildViewController(toVC!)
                self.view.insertSubview(toVC!.view, atIndex: 0)
                toVC!.didMoveToParentViewController(self)
            }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        navigationItem.hidesBackButton = true
        loadMainScoreTableView()
        
        quizesViewController =
            storyboard?.instantiateViewControllerWithIdentifier("Quiz")
            as! TrailQuizViewController
        
        quizesViewController.loadTableView()
        
        quizesViewController.view.frame = view.frame
        switchViewController(from: nil, to: quizesViewController)
    }
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
        // Dispose of any resources that can be recreated.
        if quizesViewController != nil
            && quizesViewController!.view.superview == nil {
                quizesViewController = nil
        }
        
        if scoreReportViewController != nil
            && scoreReportViewController!.view.superview == nil {
                scoreReportViewController = nil
        }
        
        if todayQuizViewController != nil
            && todayQuizViewController!.view.superview == nil {
                todayQuizViewController = nil
        }
    }
    
    @IBAction func switchViews(sender: UIBarButtonItem) {
        let buttonclicked=sender.title!
        
        // Code added for animation
        UIView.beginAnimations("View Flip", context: nil)
        UIView.setAnimationDuration(8)
        UIView.setAnimationCurve(.EaseInOut)

        if( buttonclicked=="Quizzes"){
            if quizesViewController?.view.superview == nil {
                if quizesViewController == nil {
                    quizesViewController =
                        storyboard?.instantiateViewControllerWithIdentifier("Quiz") as! TrailQuizViewController
                }
                if scoreReportViewController != nil
                    && scoreReportViewController!.view.superview != nil{
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        quizesViewController.view.frame = view.frame
                        switchViewController(from:scoreReportViewController, to: quizesViewController)
                }else if todayQuizViewController != nil
                    && todayQuizViewController!.view.superview != nil{
                       // Code added for animation
                        // UIView.setAnimationTransition(, forView: <#T##UIView#>, cache: <#T##Bool#>)
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        
                        quizesViewController.view.frame = view.frame
                        switchViewController(from:todayQuizViewController, to: quizesViewController)
                }else{
                     UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                    
                    quizesViewController.view.frame = view.frame
                    switchViewController(from:nil, to: quizesViewController)
                }
            }
        }else if( buttonclicked=="Score Report"){
            //print("@@@@@@@@@@@@@@@@@@@ mainQuizScores =\(mainQuizScores.count) @@@@@@@@@@@@@@@@@@@@@")
            if scoreReportViewController?.view.superview == nil {
                if scoreReportViewController == nil {
                    scoreReportViewController =
                        storyboard?.instantiateViewControllerWithIdentifier("ScoreReport") as! ScoreReportViewController
                    scoreReportViewController.setQuizScores(mainQuizScores)
                    
                }
                if quizesViewController != nil
                    && quizesViewController!.view.superview != nil{
                        // Code added for animation
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        scoreReportViewController.view.frame = view.frame
                        switchViewController(from:quizesViewController, to: scoreReportViewController)
                }else if todayQuizViewController != nil
                    && todayQuizViewController!.view.superview != nil{
                        
                        scoreReportViewController.loadScoreTableView()
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        
                        scoreReportViewController.view.frame = view.frame
                        switchViewController(from:todayQuizViewController, to: scoreReportViewController)
                }else{
                    UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                    
                    scoreReportViewController.view.frame = view.frame
                    switchViewController(from:nil, to: scoreReportViewController)
                }
            }
        }else if( buttonclicked=="Quiz Today"){
            if todayQuizViewController?.view.superview == nil {
                if todayQuizViewController == nil {
                    todayQuizViewController =
                        storyboard?.instantiateViewControllerWithIdentifier("QuizToday") as! TodayQuizViewController
                    
                }
                if quizesViewController != nil
                    && quizesViewController!.view.superview != nil{
                        // Code added for animation
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        todayQuizViewController.view.frame = view.frame
                        switchViewController(from:quizesViewController, to: todayQuizViewController)
                }else if scoreReportViewController != nil
                    && scoreReportViewController!.view.superview != nil{
                        UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                        
                        todayQuizViewController.view.frame = view.frame
                        switchViewController(from:scoreReportViewController, to: todayQuizViewController)
                }else{
                    UIView.setAnimationTransition(.CurlUp, forView: view, cache: true)
                    
                    todayQuizViewController.view.frame = view.frame
                    switchViewController(from:nil, to: todayQuizViewController)
                }
            }
            
        }else if( buttonclicked=="Log Out"){
            PFUser.logOut()
            
            let logInViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("LogIn") as? LogInViewController
            self.navigationController?.pushViewController(logInViewControllerObj!, animated: true)
        }// Code added for animation
        UIView.commitAnimations()
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
