pipeline{
    
    agent any
    stages{
        stage("Build"){
            steps{
                echo("Building")
            }
        }
        
        stage("deploy to Dev"){
            steps{
                echo("deploy to Dev")
            }
        }
        stage("deploy to qa"){
            steps{
                echo("deploy to qa")
            }
        }
        
        stage("regression test on qa"){
            steps{
                echo("regression test on qa")
            }
        }
        
        stage("deploy to stage"){
            steps{
                echo("deploy to stage")
            }
        }
        
        stage("sanity test"){
            steps{
                echo("sanity test")
            }
        }
        
        stage("regression test"){
            steps{
                echo("regression test")
            }
        }
        
        stage("deploy to prod"){
            steps{
                echo("deploy to prod")
            }
        }
        
    }
}