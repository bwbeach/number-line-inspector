# number-line-inspector

This is a sample inspector plug-in for IntelliJ IDEA.  I did it purely
as a learning exercise; it's not intended to be a useful inspector.

I have an idiosyncracy in my coding style: when I have an inequality
expression, I always use `<` and never use `>`.  It's easier for me
to visualize what's happening if the things appear in number-line order.

This inspector will flag uses of `>` as errors, and provide a remedy
that switches the order of the operands and switches to '<'.

I did a few hours of initial exploration, so these steps are in exactly
the order I learned things.  After figuring out a couple key pieces,
I started a fresh repository on GitHub, wrote this introduction,
and then ...

## New GitHub repository

After creating the new repo, I cloned it locally.

## Download IntelliJ IDEA

Download the community edition of [IntelliJ IDEA](https://www.jetbrains.com/idea/download).
It's free.

## Configure Java 8 as the default JDK

The splash screen that comes up when you start IDEA has a configuration
drop-down at the lower right.  The "Project Structure for New Projects"
lets you choose the default JDK.  I downloaded adopt-openj9-1.8 and set it
as the default, with Java 8 as the default language level.

## Create a new project

Following the directions from IntelliJ, I created a new Gradle project
with "Java" and "IntelliJ Platform Plugin" as the selected Libraries/Frameworks.
The project is called "number-line-inspector", and it's in the newly created
git repository.

After creating the new project, I followed advice from some web page
and set the JDK version to 1.8 for Gradle in the IDEA preferences:

 * Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM

Then, before the first commit, I added these to `.gitignore`:

    # Tools
    .idea
    .gradle
    build.gradle

## Install PsiViewer plugin

The inspector is going to work by examining the abstract syntax tree
that represents a Java program.  IDEA uses a system called [Program
Structure Interface (PSI)](https://www.jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi.html)
to represent the code you're working on.  This is what the plugin
will need to look at.

There's a plugin for IDEA called [PsiViewer](https://plugins.jetbrains.com/plugin/227-psiviewer) 
that will display the PSI for the code you're workin on.  It's super 
helpful for figuring out what things the code will need to look at.

After you install the plugin, there's a button on the right edge of the editing pane
that turns on the viewer.


