#!/usr/bin/env python
# coding: utf-8

# In[4]:


import os
import subprocess
import streamlit as st
from git import Repo


def main():
    st.title("Git Repository Analyzer")
    git_url = st.text_input("Paste Git Repository Link", "")

    if st.button("Analyze"):
        current_dir = os.getcwd()
        repo_dir = os.path.join(current_dir, "my_repository")
        clone_repository(git_url, repo_dir)
        analyze_repository(repo_dir)


def clone_repository(git_url, repo_dir):
    if not os.path.exists(repo_dir):
        os.makedirs(repo_dir)
    st.write("Cloning repo {} into {}".format(git_url, repo_dir))
    Repo.clone_from(git_url, repo_dir)


def analyze_file(file_path):
    result = subprocess.run(['bandit', '-r', file_path], capture_output=True, text=True)
    st.code(result.stdout)


def analyze_repository(repo_dir):
    for root, dirs, files in os.walk(repo_dir):
        for file in files:
            if file.endswith('.py'):
                file_path = os.path.join(root, file)
                st.write("Analyzing file: {}".format(file_path))
                analyze_file(file_path)


if __name__ == "__main__":
    main()


# In[ ]:




