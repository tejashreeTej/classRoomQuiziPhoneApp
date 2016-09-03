//
//  MultipleChoiceViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 06/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class TodayQuizViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var startButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        startButton.enabled = false
        self.titleLabel.text="No Quiz Today !"
        let query=PFQuery(className: "QuizToday")
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            print("iPhoneQuiz")
            if error == nil {
                print("iPhoneQuiz 1")
                if (objects != nil){
                     print("iPhoneQuiz 2")
                    if objects?.count>0{
                         print("iPhoneQuiz 3")
                        self.startButton.enabled = true
                        self.titleLabel.text="Start your Quiz"
                    }
                }else{
                    self.startButton.enabled = false
                    self.titleLabel.text="No Quiz Today !"
                }
                
                
            }else{
                self.startButton.enabled = false
                self.titleLabel.text="No Quiz Today !"
            }
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
        
    }
    
    @IBAction func startQuiz(sender: UIButton) {
        print("startQuiz")
        let multipleViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Multiple") as? MultipleChoiceViewController
                print("startQuiz1")
        self.navigationController?.pushViewController(multipleViewControllerObj!, animated: true)

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
